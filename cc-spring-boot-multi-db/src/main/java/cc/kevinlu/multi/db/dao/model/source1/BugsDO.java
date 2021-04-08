package cc.kevinlu.multi.db.dao.model.source1;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* bf_bugs
* 
* @author chuanchuan
* 
* @time 2020-10-13
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BugsDO implements Serializable {
    /**
     * 数据库唯一编号（自增长）
     */
    private Integer           id;

    /**
     * 问题ID
     */
    private String            bugId;

    /**
     * 发布人id
     */
    private String            userId;

    /**
     * 标题
     */
    private String            title;

    /**
     * 状态(0:暂存;1:进行中;2:已解决;3:已关闭;4禁)
     */
    private Integer           status;

    /**
     * 删除标识(0:已删除1:未删除)
     */
    private Integer           isDeleted;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 更新时间
     */
    private Date              updateTime;

    /**
     * 内容
     */
    private String            content;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bugId=").append(bugId);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", status=").append(status);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
