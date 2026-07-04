package xuanmi.ning.user.domain.model.aggregate;

import lombok.Getter;
import xuanmi.ning.common.events.DomainEvent;
import xuanmi.ning.user.domain.event.UserEvent;
import xuanmi.ning.user.domain.model.valueobject.Password;
import xuanmi.ning.user.domain.model.valueobject.UserAggId;
import xuanmi.ning.user.domain.model.valueobject.Username;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户聚合根
 */
@Getter
public class UserAgg {

    /**
     * 聚合根标识符（值对象）
     */
    private UserAggId userAggId;

    /**
     * 值对象
     */
    private Username username;
    private Password password;

    private List<DomainEvent> events = new ArrayList<>();

    public static UserAgg init(UserAggId userAggId, Username username, Password password) {
        // 聚合根创建只校验自身的参数 一些复杂校验应该放到真实服务执行的时候去校验
        UserAgg userAgg = new UserAgg();
        userAgg.userAggId = userAggId;
        userAgg.username = username;
        userAgg.password = password;
        return userAgg;
    }

    /**
     * 检测用户
     */
    public Boolean check() {
        return true;
    }

    /**
     * 登录
     */
    public Boolean login() {
        UserEvent userEvent = new UserEvent(userAggId.getUserId(), username.getValue(), "C", "C");
        addEvent(userEvent);
        return true;
    }

    public Boolean pay() {
        UserEvent userEvent = new UserEvent(userAggId.getUserId(), username.getValue(), "U", "C");
        addEvent(userEvent);
        return true;
    }

    void addEvent(DomainEvent event) {
        events.add(event);
    }
}
