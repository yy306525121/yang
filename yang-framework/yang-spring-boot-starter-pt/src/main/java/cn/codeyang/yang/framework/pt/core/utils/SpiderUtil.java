package cn.codeyang.yang.framework.pt.core.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.jsoup.nodes.Element;

@UtilityClass
public class SpiderUtil {

    public Element selectFirst(Element element, String selector) {
        return element.select(selector).first();
    }
    public String selectString(Element element, String selector) {
        Element titleElement = selectFirst(element, selector);
        if (titleElement != null) {
            return titleElement.text().strip();
        }

        return StrUtil.EMPTY;
    }

    public Integer selectInt(Element element, String selector) {
        Element titleElement = selectFirst(element, selector);
        if (titleElement != null) {
            String value = titleElement.text().strip();
            if (NumberUtil.isNumber(value)) {
                return Integer.valueOf(value);
            } else {
                throw new RuntimeException("value is not number");
            }
        }

        return 0;
    }
}
