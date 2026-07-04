package xuanmi.ning.user.infrastructure.repository.mapper;

import org.springframework.stereotype.Repository;
import xuanmi.ning.user.infrastructure.repository.po.QualificationPO;

import java.util.List;

/**
 * 资质表 Mapper
 * <p>
 * 对应数据库表: t_qualification
 */
@Repository
public interface QualificationMapper {
    /** 根据ID查询 */
    QualificationPO selectById(Long id);
    /** 根据用户ID查询所有资质 */
    List<QualificationPO> selectByUserId(Long userId);
    /** 插入 */
    int insert(QualificationPO qualificationPO);
    /** 更新 */
    int updateById(QualificationPO qualificationPO);
    /** 删除 */
    int deleteById(Long id);
}
