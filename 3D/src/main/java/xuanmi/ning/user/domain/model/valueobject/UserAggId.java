package xuanmi.ning.user.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 用户聚合根标识符 - 值对象（不可变）
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class UserAggId {
    private final Long userId;
}
