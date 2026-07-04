package xuanmi.ning.pay.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付聚合根标识符 - 值对象
 */
@Getter
@AllArgsConstructor
public class PayAggId {
    private final Long payId;
}
