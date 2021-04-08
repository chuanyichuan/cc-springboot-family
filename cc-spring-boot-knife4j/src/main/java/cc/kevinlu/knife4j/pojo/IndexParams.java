package cc.kevinlu.knife4j.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "index入参")
public class IndexParams {

    @ApiModelProperty(value = "名称")
    private String name;

}
