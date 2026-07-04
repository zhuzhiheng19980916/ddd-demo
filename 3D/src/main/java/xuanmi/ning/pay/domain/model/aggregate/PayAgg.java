package xuanmi.ning.pay.domain.model.aggregate;

import lombok.Builder;
import lombok.Getter;
import xuanmi.ning.common.events.DomainEvent;
import xuanmi.ning.pay.domain.event.PayEvent;
import xuanmi.ning.pay.domain.model.valueobject.Money;
import xuanmi.ning.pay.domain.model.valueobject.PayAggId;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付聚合根
 */
@Getter
@Builder
public class PayAgg {
    /** 聚合根标识符 */
    private final PayAggId payAggId;
    /** 支付金额 */
    private final Money money;
    /** 关联用户ID */
    private final Long userId;
    /** 支付状态: PENDING / SUCCESS / FAILED */
    private final String status;
    /** 领域事件列表 */
    @Builder.Default
    private List<DomainEvent> events = new ArrayList<>();

    void addEvent(DomainEvent event) {
        events.add(event);
    }

    public void pay() {
        PayEvent payEvent = new PayEvent(money, payAggId, userId, status, "c", "c");
        addEvent(payEvent);
    }
}
