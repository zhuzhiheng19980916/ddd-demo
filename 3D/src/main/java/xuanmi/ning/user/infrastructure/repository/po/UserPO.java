package xuanmi.ning.user.infrastructure.repository.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表 (t_user) 持久化对象
 * <p>
 * 对应领域模型: {@link xuanmi.ning.user.domain.model.aggregate.UserAgg}
 * 值对象 Username/Password 在此展开为平铺字段
 */
@Data
public class UserPO {
    /** 主键ID */
    private Long id;
    /** 用户名 */
    private String username;
    /** 密码（加密存储） */
    private String password;
    /** 创建时间 */
    private LocalDateTime createTime;
    /** 更新时间 */
    private LocalDateTime updateTime;
}
