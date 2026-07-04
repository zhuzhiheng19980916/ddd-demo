package xuanmi.ning.user.domain.repository;

import xuanmi.ning.user.domain.model.aggregate.UserAgg;

/**
 * 用户命令仓储接口 - 定义在领域层
 */
public interface IUserCommandRepository {
    void save(UserAgg userAgg);
}
