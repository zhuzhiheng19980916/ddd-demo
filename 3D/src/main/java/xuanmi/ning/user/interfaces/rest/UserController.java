package xuanmi.ning.user.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanmi.ning.common.result.Result;
import xuanmi.ning.user.application.UserAppApplication;
import xuanmi.ning.user.application.command.LoginCommand;

/**
 * 用户限界上下文 - REST 接口层
 * 暴露用户相关的 API 端点
 */
@RestController
public class UserController {

    @Autowired
    private UserAppApplication userAppApplication;

    @GetMapping("/login")
    public Object login(LoginCommand command) {
        userAppApplication.login(command);
        return Result.success();
    }
}
