package cn.codeyang.yang.framework.pt.request.impl;

import cn.codeyang.yang.framework.pt.request.Site;
import cn.hutool.http.HttpRequest;

public class SpringSundaySite implements Site {
    @Override
    public String search() {
        HttpRequest request = HttpRequest.get("https://springsunday.net/torrents.php?cat501=1&internal=&selfrelease=&animation=&exclusive=&pack=&untouched=&selfpurchase=&mandarin=&subtitlezh=&subtitlesp=&selfcompile=&dovi=&hdr10=&hdr10plus=&hdrvivid=&hlg=&cc=&3d=&request=&contest=&incldead=0&spstate=0&pick=0&inclbookmarked=0&my=&search=%E4%BD%A0%E5%A5%BD&search_area=0&search_mode=0").header("Cookie", "SPRINGID=kc2ilriq1tkb8t381onndn9rb2u8ajif0i54dfokblha7eoenmtpsn2t387kf3rh");
        String body = request.execute().body();
        return "";
    }
}
