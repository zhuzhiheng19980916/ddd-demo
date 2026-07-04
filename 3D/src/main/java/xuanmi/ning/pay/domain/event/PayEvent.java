package xuanmi.ning.pay.domain.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import xuanmi.ning.common.events.DomainEvent;
import xuanmi.ning.pay.domain.model.valueobject.Money;
import xuanmi.ning.pay.domain.model.valueobject.PayAggId;

/**
 * 支付领域事件
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PayEvent extends DomainEvent {
    /** 支付聚合根标识符 */
    private final PayAggId payAggId;
    /** 支付金额 */
    private final Money money;
    /** 关联用户ID */
    private final Long userId;
    /** 支付状态 */
    private final String status;

    public PayEvent() {
        super("none", "none");
        this.payAggId = null;
        this.money = null;
        this.userId = null;
        this.status = null;
    }

    public PayEvent(Money money, PayAggId payAggId, Long userId, String status, String type, String label) {
        super(type, label);
        this.money = money;
        this.payAggId = payAggId;
        this.userId = userId;
        this.status = status;
    }
}
