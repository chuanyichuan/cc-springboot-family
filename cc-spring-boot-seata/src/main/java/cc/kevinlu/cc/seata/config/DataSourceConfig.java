package cc.kevinlu.cc.seata.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScans(value = {
        @MapperScan(value = "cc.kevinlu.multi.db.dao.mapper.bugfree.", sqlSessionFactoryRef = "sqlSessionFactory1"),
        @MapperScan(value = "cc.kevinlu.multi.db.dao.mapper.codeac", sqlSessionFactoryRef = "sqlSessionFactory2") })
public class DataSourceConfig {

    @Resource
    private DataSourcePrimaryProperties dataSourcePrimaryProperties;

    @Resource
    private DataSourceSecondProperties  dataSourceSecondProperties;

    @Bean(name = "dataSource1")
    @Primary
    public DataSource dataSource1() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.username(dataSourcePrimaryProperties.getUsername());
        builder.password(dataSourcePrimaryProperties.getPassword());
        builder.url(dataSourcePrimaryProperties.getUrl());
        builder.driverClassName(dataSourcePrimaryProperties.getDriverClassName());
        return builder.build();
    }

    @Bean(name = "sqlSessionFactory1")
    @Autowired
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSourceTest) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceTest);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper1/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("dataSource1") DataSource dataSourceHatsUser)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory1(dataSourceHatsUser));
    }

    @Bean(name = "bugfree_transaction")
    public PlatformTransactionManager prodTransactionManager1(@Qualifier("dataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // ===========二号连接=============

    @Bean(name = "dataSource2")
    public DataSource dataSource2() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.username(dataSourceSecondProperties.getUsername());
        builder.password(dataSourceSecondProperties.getPassword());
        builder.url(dataSourceSecondProperties.getUrl());
        builder.driverClassName(dataSourceSecondProperties.getDriverClassName());
        return builder.build();
    }

    @Bean(name = "sqlSessionFactory2")
    @Autowired
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSourceTest) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceTest);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper2/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("dataSource2") DataSource dataSourceHatsUser)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory2(dataSourceHatsUser));
    }

    @Bean(name = "codeac_transaction")
    public PlatformTransactionManager prodTransactionManager2(@Qualifier("dataSource2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
