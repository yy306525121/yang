package cn.codeyang.yang.module.infra.api.config;

import cn.codeyang.yang.module.infra.dal.dataobject.config.ConfigDO;
import cn.codeyang.yang.module.infra.service.config.ConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 参数配置 API 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ConfigApiImpl implements ConfigApi {

    @Resource
    private ConfigService configService;

    @Override
    public String getConfigValueByKey(String key) {
        ConfigDO config = configService.getConfigByKey(key);
        return config != null ? config.getValue() : null;
    }

}
