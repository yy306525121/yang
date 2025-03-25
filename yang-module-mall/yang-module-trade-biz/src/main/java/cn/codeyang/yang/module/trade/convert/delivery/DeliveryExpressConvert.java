package cn.codeyang.yang.module.trade.convert.delivery;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.module.trade.controller.admin.delivery.vo.express.*;
import cn.codeyang.yang.module.trade.controller.admin.delivery.vo.express.DeliveryExpressCreateReqVO;
import cn.codeyang.yang.module.trade.controller.admin.delivery.vo.express.DeliveryExpressExcelVO;
import cn.codeyang.yang.module.trade.controller.admin.delivery.vo.express.DeliveryExpressRespVO;
import cn.codeyang.yang.module.trade.controller.admin.delivery.vo.express.DeliveryExpressUpdateReqVO;
import cn.codeyang.yang.module.trade.controller.app.delivery.vo.express.AppDeliveryExpressRespVO;
import cn.codeyang.yang.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeliveryExpressConvert {

    DeliveryExpressConvert INSTANCE = Mappers.getMapper(DeliveryExpressConvert.class);

    DeliveryExpressDO convert(DeliveryExpressCreateReqVO bean);

    DeliveryExpressDO convert(DeliveryExpressUpdateReqVO bean);

    DeliveryExpressRespVO convert(DeliveryExpressDO bean);

    List<DeliveryExpressRespVO> convertList(List<DeliveryExpressDO> list);

    PageResult<DeliveryExpressRespVO> convertPage(PageResult<DeliveryExpressDO> page);

    List<DeliveryExpressExcelVO> convertList02(List<DeliveryExpressDO> list);

    List<DeliveryExpressSimpleRespVO> convertList1(List<DeliveryExpressDO> list);

    List<AppDeliveryExpressRespVO> convertList03(List<DeliveryExpressDO> list);

}
