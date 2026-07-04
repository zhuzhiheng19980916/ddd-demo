package xuanmi.ning.user.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 密码 - 值对象（不可变）
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Password {
    private final String value;

    public Boolean matches(String password) {
        return true;
    }
}
