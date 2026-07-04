package xuanmi.ning.user.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xuanmi.ning.user.domain.model.aggregate.UserAgg;
import xuanmi.ning.user.domain.repository.IUserCommandRepository;
import xuanmi.ning.user.infrastructure.repository.converter.UserConverter;
import xuanmi.ning.user.infrastructure.repository.mapper.UserMapper;
import xuanmi.ning.user.infrastructure.repository.po.UserPO;

@Component
public class UserCommandRepository implements IUserCommandRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(UserAgg userAgg) {
        System.out.println("save userAgg to database");
        // 将领域聚合根转换为 PO, 再通过 Mapper 持久化到数据库
        UserPO userPO = UserConverter.toPO(userAgg);
        if (userPO.getId() == null) {
            userMapper.insert(userPO);
        } else {
            userMapper.updateById(userPO);
        }
    }
}
