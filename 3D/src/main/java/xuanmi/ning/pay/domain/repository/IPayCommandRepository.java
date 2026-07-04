package xuanmi.ning.pay.domain.repository;

import xuanmi.ning.pay.domain.model.aggregate.PayAgg;

/**
 * 支付命令仓储接口 - 定义在领域层
 */
public interface IPayCommandRepository {
    void save(PayAgg payAgg);
}
