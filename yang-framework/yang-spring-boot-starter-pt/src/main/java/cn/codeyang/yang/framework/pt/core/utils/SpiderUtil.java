package cn.codeyang.yang.framework.pt.core.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

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

    public String selectNextSiblingText(Element element, String selector) {
        Element value = selectFirst(element, selector);
        if (value == null) {
            return StrUtil.EMPTY;
        }

        Node next = value.nextSibling();
        if (next == null) {
            return StrUtil.EMPTY;
        }

        return next.toString().strip();

    }
}
