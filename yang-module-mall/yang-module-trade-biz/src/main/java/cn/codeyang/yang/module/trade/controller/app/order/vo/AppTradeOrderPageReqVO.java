package cn.codeyang.yang.module.trade.controller.app.order.vo;

import cn.codeyang.yang.framework.common.pojo.PageParam;
import cn.codeyang.yang.framework.common.validation.InEnum;
import cn.codeyang.yang.module.trade.enums.order.TradeOrderStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "交易订单分页 Request VO")
@Data
public class AppTradeOrderPageReqVO extends PageParam {

    @Schema(description = "订单状态", example = "1")
    @InEnum(value = TradeOrderStatusEnum.class, message = "订单状态必须是 {value}")
    private Integer status;

    @Schema(description = "是否评价", example = "true")
    private Boolean commentStatus;

}
