package xuanmi.ning.pay.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanmi.ning.common.result.Result;
import xuanmi.ning.pay.application.PayAppApplication;
import xuanmi.ning.pay.application.command.PayCommand;
import xuanmi.ning.pay.interfaces.dto.PayRequest;

/**
 * 支付限界上下文 - REST 接口层
 * 暴露支付相关的 API 端点
 */
@RestController
public class PayController {

    @Autowired
    private PayAppApplication payAppApplication;

    @GetMapping("/pay")
    public Result<Void> pay(PayRequest payRequest) {
        // interfaces DTO -> application Command
        PayCommand command = new PayCommand();
        command.setUserId(payRequest.getUserId());
        command.setMoney(payRequest.getMoney());

        return payAppApplication.pay(command);
    }
}
