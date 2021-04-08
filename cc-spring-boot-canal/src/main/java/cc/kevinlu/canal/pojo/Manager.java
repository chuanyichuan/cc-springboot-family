package cc.kevinlu.canal.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.ToString;

/**
 * @author chuan
 */
@Data
@ToString
public class Manager {

    private Long   id;

    private String login_name;

    private String password;

    @JSONField(name = "gmt_created")
    private Date   gmtCreated;

    private Date   gmt_updated;

}
