package xuanmi.ning.pay.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xuanmi.ning.pay.domain.model.aggregate.PayAgg;
import xuanmi.ning.pay.domain.repository.IPayQueryRepository;
import xuanmi.ning.pay.infrastructure.repository.converter.PayConverter;
import xuanmi.ning.pay.infrastructure.repository.mapper.PayMapper;
import xuanmi.ning.pay.infrastructure.repository.po.PayPO;

@Component
public class PayQueryRepository implements IPayQueryRepository {

    @Autowired
    private PayMapper payMapper;

    @Override
    public PayAgg query(String query) {
        System.out.println("query pay from database");
        // 通过 Mapper 获取 PO, 再转换为领域聚合根
        PayPO payPO = payMapper.selectById(Long.parseLong(query));
        return PayConverter.toDomain(payPO);
    }
}
