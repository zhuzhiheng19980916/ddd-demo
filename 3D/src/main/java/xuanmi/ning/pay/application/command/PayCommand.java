package xuanmi.ning.pay.application.command;

import lombok.Data;

/**
 * 支付应用层命令
 */
@Data
public class PayCommand {
    private Long userId;
    private String money;
}
