package xuanmi.ning.pay.infrastructure.repository.converter;

import xuanmi.ning.pay.domain.model.aggregate.PayAgg;
import xuanmi.ning.pay.domain.model.valueobject.Money;
import xuanmi.ning.pay.domain.model.valueobject.PayAggId;
import xuanmi.ning.pay.infrastructure.repository.po.PayPO;

/**
 * Pay 持久化对象 ↔ 领域模型 转换器
 * <p>
 * 负责 PayAggId、Money 等值对象与数据库平铺字段之间的转换
 */
public class PayConverter {

    /**
     * PO → Domain (组装聚合根)
     * 将数据库平铺字段组装为包含值对象的领域聚合根
     */
    public static PayAgg toDomain(PayPO po) {
        if (po == null) {
            return null;
        }
        PayAggId payAggId = new PayAggId(po.getId());
        Money money = new Money(po.getMoneyValue(), po.getMoneyUnit());
        return PayAgg.builder()
                .payAggId(payAggId)
                .money(money)
                .userId(po.getUserId())
                .status(po.getStatus())
                .build();
    }

    /**
     * Domain → PO (展开值对象)
     * 将领域聚合根的值对象展开为数据库平铺字段
     */
    public static PayPO toPO(PayAgg agg) {
        if (agg == null) {
            return null;
        }
        PayPO po = new PayPO();
        if (agg.getPayAggId() != null) {
            po.setId(agg.getPayAggId().getPayId());
        }
        if (agg.getMoney() != null) {
            po.setMoneyValue(agg.getMoney().getValue());
            po.setMoneyUnit(agg.getMoney().getUnit());
        }
        po.setUserId(agg.getUserId());
        po.setStatus(agg.getStatus());
        return po;
    }
}
