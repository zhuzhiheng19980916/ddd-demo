package xuanmi.ning.pay.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 金额 - 值对象（不可变）
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Money {
    private final BigDecimal value;
    private final Integer unit;
}
