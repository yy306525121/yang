package cn.codeyang.yang.module.promotion.api.combination;

import cn.codeyang.yang.framework.common.util.object.BeanUtils;
import cn.codeyang.yang.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import cn.codeyang.yang.module.promotion.api.combination.dto.CombinationRecordCreateRespDTO;
import cn.codeyang.yang.module.promotion.api.combination.dto.CombinationRecordRespDTO;
import cn.codeyang.yang.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import cn.codeyang.yang.module.promotion.convert.combination.CombinationActivityConvert;
import cn.codeyang.yang.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import cn.codeyang.yang.module.promotion.service.combination.CombinationRecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 拼团活动 API 实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class CombinationRecordApiImpl implements CombinationRecordApi {

    @Resource
    private CombinationRecordService combinationRecordService;

    @Override
    public void validateCombinationRecord(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        combinationRecordService.validateCombinationRecord(userId, activityId, headId, skuId, count);
    }

    @Override
    public CombinationRecordCreateRespDTO createCombinationRecord(CombinationRecordCreateReqDTO reqDTO) {
        return CombinationActivityConvert.INSTANCE.convert4(combinationRecordService.createCombinationRecord(reqDTO));
    }

    @Override
    public CombinationRecordRespDTO getCombinationRecordByOrderId(Long userId, Long orderId) {
        CombinationRecordDO record = combinationRecordService.getCombinationRecord(userId, orderId);
        return BeanUtils.toBean(record, CombinationRecordRespDTO.class);
    }

    @Override
    public CombinationValidateJoinRespDTO validateJoinCombination(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        return combinationRecordService.validateJoinCombination(userId, activityId, headId, skuId, count);
    }

}
