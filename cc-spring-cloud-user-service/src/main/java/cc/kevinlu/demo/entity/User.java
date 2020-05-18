package cc.kevinlu.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private static final long serialVersionUID = 1L;

    private Integer           id;
    private String            name;
    private String            password;
}
