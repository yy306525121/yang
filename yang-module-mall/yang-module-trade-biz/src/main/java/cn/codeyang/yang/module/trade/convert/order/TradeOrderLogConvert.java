package cn.codeyang.yang.module.trade.convert.order;

import cn.codeyang.yang.module.trade.dal.dataobject.order.TradeOrderLogDO;
import cn.codeyang.yang.module.trade.service.order.bo.TradeOrderLogCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeOrderLogConvert {

    TradeOrderLogConvert INSTANCE = Mappers.getMapper(TradeOrderLogConvert.class);

    TradeOrderLogDO convert(TradeOrderLogCreateReqBO bean);

}
