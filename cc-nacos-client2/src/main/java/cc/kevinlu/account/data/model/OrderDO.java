package cc.kevinlu.account.data.model;

import java.io.Serializable;

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
public class OrderDO implements Serializable {
    /**
     * id
     */
    private Integer           id;

    /**
     * user_id
     */
    private String            userId;

    /**
     * commodity_code
     */
    private String            commodityCode;

    /**
     * count
     */
    private Integer           count;

    /**
     * money
     */
    private Integer           money;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", commodityCode=").append(commodityCode);
        sb.append(", count=").append(count);
        sb.append(", money=").append(money);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
