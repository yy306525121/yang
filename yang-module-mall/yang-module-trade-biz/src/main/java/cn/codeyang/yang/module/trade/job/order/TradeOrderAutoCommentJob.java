package cn.codeyang.yang.module.trade.job.order;

import cn.codeyang.yang.framework.quartz.core.handler.JobHandler;
import cn.codeyang.yang.framework.tenant.core.job.TenantJob;
import cn.codeyang.yang.module.trade.service.order.TradeOrderUpdateService;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 交易订单的自动评论 Job
 *
 * @author 芋道源码
 */
@Component
public class TradeOrderAutoCommentJob implements JobHandler {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;

    @Override
    @TenantJob
    public String execute(String param) {
        int count = tradeOrderUpdateService.createOrderItemCommentBySystem();
        return String.format("评论订单 %s 个", count);
    }

}
