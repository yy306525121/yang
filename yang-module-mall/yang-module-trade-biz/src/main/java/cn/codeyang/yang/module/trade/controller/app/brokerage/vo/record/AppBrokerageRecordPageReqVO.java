package cn.codeyang.yang.module.trade.controller.app.brokerage.vo.record;

import cn.codeyang.yang.framework.common.pojo.PageParam;
import cn.codeyang.yang.framework.common.validation.InEnum;
import cn.codeyang.yang.module.trade.enums.brokerage.BrokerageRecordBizTypeEnum;
import cn.codeyang.yang.module.trade.enums.brokerage.BrokerageRecordStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.codeyang.yang.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "应用 App - 分销记录分页 Request VO")
@Data
public class AppBrokerageRecordPageReqVO extends PageParam {

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "业务类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(value = BrokerageRecordBizTypeEnum.class, message = "业务类型必须是 {value}")
    private Integer bizType;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(value = BrokerageRecordStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;

}
