package cc.kevinlu.springbootkafka.entity;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {

    private Long   id;
    private String msg;
    private Date   sendTime;

}
