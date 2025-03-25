package cn.codeyang.yang.module.pay.api.transfer;

import cn.codeyang.yang.framework.common.util.object.BeanUtils;
import cn.codeyang.yang.module.pay.api.transfer.dto.PayTransferCreateReqDTO;
import cn.codeyang.yang.module.pay.api.transfer.dto.PayTransferRespDTO;
import cn.codeyang.yang.module.pay.dal.dataobject.transfer.PayTransferDO;
import cn.codeyang.yang.module.pay.service.transfer.PayTransferService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 转账单 API 实现类
 *
 * @author jason
 */
@Service
@Validated
public class PayTransferApiImpl implements PayTransferApi {

    @Resource
    private PayTransferService payTransferService;

    @Override
    public Long createTransfer(PayTransferCreateReqDTO reqDTO) {
        return payTransferService.createTransfer(reqDTO);
    }

    @Override
    public PayTransferRespDTO getTransfer(Long id) {
        PayTransferDO transfer = payTransferService.getTransfer(id);
        return BeanUtils.toBean(transfer, PayTransferRespDTO.class);
    }

}
