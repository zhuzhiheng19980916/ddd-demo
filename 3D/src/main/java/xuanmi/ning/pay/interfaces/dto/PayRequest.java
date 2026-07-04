package xuanmi.ning.pay.interfaces.dto;

import lombok.Data;

/**
 * 支付请求 DTO
 */
@Data
public class PayRequest {
    private Long userId;
    private String money;
}
