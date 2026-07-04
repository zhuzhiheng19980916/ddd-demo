package xuanmi.ning.pay.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xuanmi.ning.pay.domain.model.aggregate.PayAgg;
import xuanmi.ning.pay.domain.repository.IPayCommandRepository;
import xuanmi.ning.pay.infrastructure.repository.converter.PayConverter;
import xuanmi.ning.pay.infrastructure.repository.mapper.PayMapper;
import xuanmi.ning.pay.infrastructure.repository.po.PayPO;

@Component
public class PayCommandRepository implements IPayCommandRepository {

    @Autowired
    private PayMapper payMapper;

    @Override
    public void save(PayAgg payAgg) {
        System.out.println("save payAgg to database");
        // 将领域聚合根转换为 PO, 再通过 Mapper 持久化到数据库
        PayPO payPO = PayConverter.toPO(payAgg);
        if (payPO.getId() == null) {
            payMapper.insert(payPO);
        } else {
            payMapper.updateById(payPO);
        }
    }
}
