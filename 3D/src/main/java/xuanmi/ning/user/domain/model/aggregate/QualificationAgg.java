package xuanmi.ning.user.domain.model.aggregate;

import lombok.Getter;

/**
 * 资质聚合根
 */
@Getter
public class QualificationAgg {
    /** 聚合根标识符 */
    private Long id;
    /** 关联用户ID */
    private Long userId;
    /** 资质类型 */
    private String type;
    /** 资质状态: ACTIVE / EXPIRED / REVOKED */
    private String status;
}
