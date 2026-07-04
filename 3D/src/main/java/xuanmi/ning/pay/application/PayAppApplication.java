package xuanmi.ning.pay.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xuanmi.ning.common.events.DomainEvent;
import xuanmi.ning.common.events.EventBus;
import xuanmi.ning.common.result.Result;
import xuanmi.ning.pay.application.command.PayCommand;
import xuanmi.ning.pay.domain.model.aggregate.PayAgg;
import xuanmi.ning.pay.domain.repository.IPayCommandRepository;
import xuanmi.ning.pay.domain.repository.IPayQueryRepository;
import xuanmi.ning.pay.domain.service.PayDomainService;

@Component
public class PayAppApplication {

    @Autowired
    private PayDomainService payDomainService;
    @Autowired
    private IPayQueryRepository payQueryRepository;
    @Autowired
    private IPayCommandRepository payCommandRepository;
    @Autowired
    private EventBus eventBus;

    public Result<Void> pay(PayCommand payCommand) {
        // 查询聚合根
        PayAgg payAgg = payQueryRepository.query(payCommand.getUserId() + payCommand.getMoney());

        // 调用领域服务
        payDomainService.pay(payAgg);

        // 持久化聚合根
        payCommandRepository.save(payAgg);

        // 发布事件
        for (DomainEvent event : payAgg.getEvents()) {
            eventBus.publish(event);
        }

        return Result.success();
    }
}
