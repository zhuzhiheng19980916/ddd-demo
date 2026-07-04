package xuanmi.ning.user.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanmi.ning.common.result.DomainResult;
import xuanmi.ning.common.result.Result;
import xuanmi.ning.user.application.UserAppApplication;
import xuanmi.ning.user.application.command.LoginCommand;
import xuanmi.ning.user.interfaces.dto.LoginRequest;

/**
 * 用户限界上下文 - REST 接口层
 * 暴露用户相关的 API 端点
 */
@RestController
public class UserController {

    @Autowired
    private UserAppApplication userAppApplication;

    @GetMapping("/login")
    public Result<DomainResult> login(LoginRequest request) {
        // interfaces DTO -> application Command
        LoginCommand command = new LoginCommand();
        command.setUsername(request.getUsername());
        command.setPassword(request.getPassword());

        return userAppApplication.login(command);
    }
}
