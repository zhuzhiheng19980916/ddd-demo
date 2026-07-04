package xuanmi.ning.user.infrastructure.repository.converter;

import xuanmi.ning.user.domain.model.aggregate.UserAgg;
import xuanmi.ning.user.domain.model.valueobject.Password;
import xuanmi.ning.user.domain.model.valueobject.UserAggId;
import xuanmi.ning.user.domain.model.valueobject.Username;
import xuanmi.ning.user.infrastructure.repository.po.UserPO;

/**
 * User 持久化对象 ↔ 领域模型 转换器
 * <p>
 * 负责将数据库平铺字段组装为领域值对象，或将值对象展开为平铺字段。
 * 转换逻辑属于基础设施层的职责，领域层不感知 PO 的存在。
 */
public class UserConverter {

    /**
     * PO → Domain (组装聚合根)
     * 将数据库平铺字段组装为包含值对象的领域聚合根
     */
    public static UserAgg toDomain(UserPO po) {
        if (po == null) {
            return null;
        }
        UserAggId userAggId = new UserAggId(po.getId());
        Username username = new Username(po.getUsername());
        Password password = new Password(po.getPassword());
        return UserAgg.init(userAggId, username, password);
    }

    /**
     * Domain → PO (展开值对象)
     * 将领域聚合根的值对象展开为数据库平铺字段
     */
    public static UserPO toPO(UserAgg agg) {
        if (agg == null) {
            return null;
        }
        UserPO po = new UserPO();
        if (agg.getUserAggId() != null) {
            po.setId(agg.getUserAggId().getUserId());
        }
        if (agg.getUsername() != null) {
            po.setUsername(agg.getUsername().getValue());
        }
        if (agg.getPassword() != null) {
            po.setPassword(agg.getPassword().getValue());
        }
        return po;
    }
}
