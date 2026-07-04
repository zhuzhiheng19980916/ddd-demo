package xuanmi.ning.user.infrastructure.repository.mapper;

import org.springframework.stereotype.Repository;
import xuanmi.ning.user.infrastructure.repository.po.UserPO;

/**
 * 用户表 Mapper
 * <p>
 * 对应数据库表: t_user
 */
@Repository
public interface UserMapper {
    /** 根据ID查询 */
    UserPO selectById(Long id);
    /** 根据用户名查询 */
    UserPO selectByUsername(String username);
    /** 插入 */
    int insert(UserPO userPO);
    /** 更新 */
    int updateById(UserPO userPO);
    /** 删除 */
    int deleteById(Long id);
}
