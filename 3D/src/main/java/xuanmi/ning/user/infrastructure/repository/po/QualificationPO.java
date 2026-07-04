package xuanmi.ning.user.infrastructure.repository.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 资质表 (t_qualification) 持久化对象
 * <p>
 * 对应领域模型: {@link xuanmi.ning.user.domain.model.aggregate.QualificationAgg}
 */
@Data
public class QualificationPO {
    /** 主键ID */
    private Long id;
    /** 关联用户ID */
    private Long userId;
    /** 资质类型 */
    private String type;
    /** 资质状态: ACTIVE / EXPIRED / REVOKED */
    private String status;
    /** 创建时间 */
    private LocalDateTime createTime;
    /** 更新时间 */
    private LocalDateTime updateTime;
}
