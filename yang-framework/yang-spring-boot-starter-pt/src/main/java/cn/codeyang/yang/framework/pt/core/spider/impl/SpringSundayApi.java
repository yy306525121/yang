package cn.codeyang.yang.framework.pt.core.spider.impl;

import cn.codeyang.yang.framework.pt.config.YangPTProperties;
import cn.codeyang.yang.framework.pt.core.spider.PTApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpringSundayApi implements PTApi {
    private final YangPTProperties yangPTProperties;

    @Override
    public YangPTProperties getYangPTProperties() {
        return yangPTProperties;
    }
}
