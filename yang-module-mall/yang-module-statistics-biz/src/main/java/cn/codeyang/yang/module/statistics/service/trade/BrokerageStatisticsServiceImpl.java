package cn.codeyang.yang.module.statistics.service.trade;

import cn.codeyang.yang.module.statistics.dal.mysql.trade.BrokerageStatisticsMapper;
import cn.codeyang.yang.module.trade.enums.brokerage.BrokerageRecordBizTypeEnum;
import cn.codeyang.yang.module.trade.enums.brokerage.BrokerageRecordStatusEnum;
import cn.codeyang.yang.module.trade.enums.brokerage.BrokerageWithdrawStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 分销统计 Service 实现类
 *
 * @author owen
 */
@Service
@Validated
public class BrokerageStatisticsServiceImpl implements BrokerageStatisticsService {

    @Resource
    private BrokerageStatisticsMapper brokerageStatisticsMapper;

    @Override
    public Integer getBrokerageSettlementPriceSummary(LocalDateTime beginTime, LocalDateTime endTime) {
        return brokerageStatisticsMapper.selectSummaryPriceByStatusAndUnfreezeTimeBetween(
                BrokerageRecordBizTypeEnum.ORDER.getType(), BrokerageRecordStatusEnum.SETTLEMENT.getStatus(),
                beginTime, endTime);
    }

    @Override
    public Long getWithdrawCountByStatus(BrokerageWithdrawStatusEnum status) {
        return brokerageStatisticsMapper.selectWithdrawCountByStatus(status.getStatus());
    }

}
