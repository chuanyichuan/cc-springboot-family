package cc.kevinlu.knife4j.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

@Data
@ToString
@Builder
@ApiModel(value = "index结果集")
public class IndexResult {

    @ApiModelProperty(value = "名称")
    private String name;

    @Tolerate
    public IndexResult() {
    }
}
