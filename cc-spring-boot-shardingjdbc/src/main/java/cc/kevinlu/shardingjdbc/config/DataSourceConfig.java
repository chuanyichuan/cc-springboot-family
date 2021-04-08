package cc.kevinlu.shardingjdbc.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.SneakyThrows;

@Configuration
@MapperScan(basePackages = "cc.kevinlu.shardingjdbc.mapper", sqlSessionTemplateRef = "testSqlSessionTemplate")
public class DataSourceConfig {

    @Bean
    @Primary
    @SneakyThrows
    public DataSource shardingDataSource() {
        // 创建规则配置器
        ShardingRuleConfiguration configuration = new ShardingRuleConfiguration();

        // 添加表规则
        configuration.getTableRuleConfigs().add(tableUserInfoRuleConfiguration());

        // 添加表规则，绑定表级联关系
        configuration.getTableRuleConfigs().add(tableOrderRuleConfiguration());
        configuration.getTableRuleConfigs().add(tableOrderItemRuleConfiguration());
        configuration.setBindingTableGroups(Arrays.asList("t_order", "t_order_item"));

        // 添加表规则，设置广播表(共享表/ER表/字段表)
        configuration.getTableRuleConfigs().add(tableConfigRuleConfiguration());
        configuration.setBroadcastTables(Arrays.asList("t_config"));

        configuration.setDefaultDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "user_${user_id % 2}"));
        configuration.setDefaultTableShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("order_id", new DemoTableShardingAlgorithm()));

        Properties props = new Properties();
        props.put("sql.show", true);

        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), configuration, props);
    }

    /**
     * 创建user_info表的路由规则
     * 
     * @return
     */
    public TableRuleConfiguration tableUserInfoRuleConfiguration() {
        TableRuleConfiguration configuration = new TableRuleConfiguration("user_info", "user_${0..1}.user_info");
        configuration.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "user_id"));
        return configuration;
    }

    public TableRuleConfiguration tableOrderRuleConfiguration() {
        TableRuleConfiguration configuration = new TableRuleConfiguration("t_order", "user_${0..1}.t_order");
        return configuration;
    }

    public TableRuleConfiguration tableOrderItemRuleConfiguration() {
        TableRuleConfiguration configuration = new TableRuleConfiguration("t_order_item", "user_${0..1}.t_order_item");
        return configuration;
    }

    public TableRuleConfiguration tableConfigRuleConfiguration() {
        TableRuleConfiguration configuration = new TableRuleConfiguration("t_config", "user_${0..1}.t_config");
        return configuration;
    }

    /**
     * 需要手动配置事务管理器
     *
     * @param shardingDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(shardingDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("user_0", createDataSource("user_0"));
        result.put("user_1", createDataSource("user_1"));
        return result;
    }

    private DataSource createDataSource(final String dataSourceName) {
        HikariConfig result = new HikariConfig();
        result.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        result.setJdbcUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUsername("root");
        result.setPassword("root");
        return new HikariDataSource(result);
    }

}
