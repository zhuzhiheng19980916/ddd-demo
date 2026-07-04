package xuanmi.ning.user.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 用户名 - 值对象（不可变）
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Username {
    private final String value;

    public Boolean matches(String username) {
        return true;
    }
}
