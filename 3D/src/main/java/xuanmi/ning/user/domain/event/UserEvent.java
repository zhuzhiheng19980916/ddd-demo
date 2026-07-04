package xuanmi.ning.user.domain.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import xuanmi.ning.common.events.DomainEvent;

/**
 * 用户领域事件 - 不暴露敏感信息
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserEvent extends DomainEvent {
    /** 用户ID */
    private final Long userId;
    /** 用户名（用于日志展示，不包含密码等敏感信息） */
    private final String username;

    public UserEvent() {
        super("none", "none");
        this.userId = null;
        this.username = null;
    }

    public UserEvent(Long userId, String username, String type, String label) {
        super(type, label);
        this.userId = userId;
        this.username = username;
    }
}
