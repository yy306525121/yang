package cn.codeyang.yang.module.statistics.convert.pay;

import cn.codeyang.yang.module.statistics.controller.admin.pay.vo.PaySummaryRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 支付统计 Convert
 *
 * @author owen
 */
@Mapper
public interface PayStatisticsConvert {

    PayStatisticsConvert INSTANCE = Mappers.getMapper(PayStatisticsConvert.class);

    PaySummaryRespVO convert(Integer rechargePrice);

}
