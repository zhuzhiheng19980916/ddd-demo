package xuanmi.ning.common.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 领域事件监听器 - 只依赖 DomainEvent 抽象，不依赖具体事件类型
 */
@Component
public class Listener {

    @EventListener
    public void listen(DomainEvent event) {
        System.out.println("handle domain event: " + event);
    }
}
