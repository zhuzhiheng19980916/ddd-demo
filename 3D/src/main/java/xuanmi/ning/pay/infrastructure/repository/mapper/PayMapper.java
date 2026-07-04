package xuanmi.ning.pay.infrastructure.repository.mapper;

import org.springframework.stereotype.Repository;
import xuanmi.ning.pay.infrastructure.repository.po.PayPO;

import java.util.List;

/**
 * 支付表 Mapper
 * <p>
 * 对应数据库表: t_pay
 */
@Repository
public interface PayMapper {
    /** 根据ID查询 */
    PayPO selectById(Long id);
    /** 根据用户ID查询所有支付记录 */
    List<PayPO> selectByUserId(Long userId);
    /** 插入 */
    int insert(PayPO payPO);
    /** 更新 */
    int updateById(PayPO payPO);
    /** 删除 */
    int deleteById(Long id);
}
