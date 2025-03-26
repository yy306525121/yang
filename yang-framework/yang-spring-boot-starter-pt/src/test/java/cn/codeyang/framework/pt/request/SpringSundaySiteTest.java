package cn.codeyang.framework.pt.request;

import cn.codeyang.yang.framework.pt.request.impl.SpringSundaySite;
import org.junit.jupiter.api.Test;

public class SpringSundaySiteTest {
    @Test
    public void testSearch() {
        SpringSundaySite site = new SpringSundaySite();
        String search = site.search();
    }
}
