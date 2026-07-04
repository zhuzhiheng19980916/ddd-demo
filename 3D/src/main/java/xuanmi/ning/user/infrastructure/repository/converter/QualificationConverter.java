package xuanmi.ning.user.infrastructure.repository.converter;

import xuanmi.ning.user.domain.model.aggregate.QualificationAgg;
import xuanmi.ning.user.infrastructure.repository.po.QualificationPO;

/**
 * Qualification 持久化对象 ↔ 领域模型 转换器
 */
public class QualificationConverter {

    /**
     * PO → Domain
     */
    public static QualificationAgg toDomain(QualificationPO po) {
        if (po == null) {
            return null;
        }
        QualificationAgg agg = new QualificationAgg();
        agg.setId(po.getId());
        agg.setUserId(po.getUserId());
        agg.setType(po.getType());
        agg.setStatus(po.getStatus());
        return agg;
    }

    /**
     * Domain → PO
     */
    public static QualificationPO toPO(QualificationAgg agg) {
        if (agg == null) {
            return null;
        }
        QualificationPO po = new QualificationPO();
        po.setId(agg.getId());
        po.setUserId(agg.getUserId());
        po.setType(agg.getType());
        po.setStatus(agg.getStatus());
        return po;
    }
}
