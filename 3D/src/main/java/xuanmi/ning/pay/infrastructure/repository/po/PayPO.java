package xuanmi.ning.pay.infrastructure.repository.po;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付表 (t_pay) 持久化对象
 * <p>
 * 对应领域模型: {@link xuanmi.ning.pay.domain.model.aggregate.PayAgg}
 * 值对象 Money 在此展开为平铺字段 moneyValue + moneyUnit
 * 值对象 PayAggId 映射为 id
 */
@Data
public class PayPO {
    /** 主键ID，对应 PayAggId.payId */
    private Long id;
    /** 关联用户ID */
    private Long userId;
    /** 金额数值，对应 Money.value */
    private BigDecimal moneyValue;
    /** 金额单位，对应 Money.unit */
    private Integer moneyUnit;
    /** 支付状态 */
    private String status;
    /** 创建时间 */
    private LocalDateTime createTime;
    /** 更新时间 */
    private LocalDateTime updateTime;
}
