package cn.codeyang.yang.module.pay.convert.wallet;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.module.pay.controller.admin.wallet.vo.wallet.PayWalletRespVO;
import cn.codeyang.yang.module.pay.controller.app.wallet.vo.wallet.AppPayWalletRespVO;
import cn.codeyang.yang.module.pay.dal.dataobject.wallet.PayWalletDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletConvert {

    PayWalletConvert INSTANCE = Mappers.getMapper(PayWalletConvert.class);

    AppPayWalletRespVO convert(PayWalletDO bean);

    PayWalletRespVO convert02(PayWalletDO bean);

    PageResult<PayWalletRespVO> convertPage(PageResult<PayWalletDO> page);

}
