package xuanmi.ning.user.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xuanmi.ning.user.domain.model.aggregate.UserAgg;
import xuanmi.ning.user.domain.repository.IUserQueryRepository;
import xuanmi.ning.user.infrastructure.repository.converter.UserConverter;
import xuanmi.ning.user.infrastructure.repository.mapper.UserMapper;
import xuanmi.ning.user.infrastructure.repository.po.UserPO;

@Component
public class UserQueryRepository implements IUserQueryRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserAgg query(String query) {
        System.out.println("query user from database");
        // 正常这里就是去数据库查询, 通过 Mapper 获取 PO, 再转换为领域聚合根
        // 涉及到多个数据库底层屏蔽等操作
        UserPO userPO = userMapper.selectByUsername(query);
        return UserConverter.toDomain(userPO);
    }
}
