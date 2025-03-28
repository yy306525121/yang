package cn.codeyang.yang.module.trade.service.config;

import cn.codeyang.yang.module.trade.controller.admin.config.vo.TradeConfigSaveReqVO;
import cn.codeyang.yang.module.trade.dal.dataobject.config.TradeConfigDO;

import jakarta.validation.Valid;

/**
 * 交易中心配置 Service 接口
 *
 * @author owen
 */
public interface TradeConfigService {

    /**
     * 更新交易中心配置
     *
     * @param updateReqVO 更新信息
     */
    void saveTradeConfig(@Valid TradeConfigSaveReqVO updateReqVO);

    /**
     * 获得交易中心配置
     *
     * @return 交易中心配置
     */
    TradeConfigDO getTradeConfig();

}
