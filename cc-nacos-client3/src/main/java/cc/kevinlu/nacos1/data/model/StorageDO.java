package cc.kevinlu.nacos1.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* @author lucunyu
* @time 2021-04-19
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StorageDO implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * commodity_code
     */
    private String commodityCode;

    /**
     * count
     */
    private Integer count;

    /**
     * 单价
     */
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commodityCode=").append(commodityCode);
        sb.append(", count=").append(count);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}