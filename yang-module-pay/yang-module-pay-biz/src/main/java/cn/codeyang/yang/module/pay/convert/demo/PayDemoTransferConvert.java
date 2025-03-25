package cn.codeyang.yang.module.pay.convert.demo;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import cn.codeyang.yang.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferRespVO;
import cn.codeyang.yang.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jason
 */
@Mapper
public interface PayDemoTransferConvert {

    PayDemoTransferConvert INSTANCE = Mappers.getMapper(PayDemoTransferConvert.class);

    PayDemoTransferDO convert(PayDemoTransferCreateReqVO bean);

    PageResult<PayDemoTransferRespVO> convertPage(PageResult<PayDemoTransferDO> pageResult);
}
