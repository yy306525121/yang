package cn.codeyang.yang.framework.pt.core.spider;

import cn.codeyang.yang.framework.pt.config.YangPTProperties;
import cn.codeyang.yang.framework.pt.core.domain.Torrent;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PTApi {
    YangPTProperties getYangPTProperties();

    default List<Torrent> fetchTorrents(String host, String cookie, Map<String, String> params) {
        YangPTProperties yangPTProperties = getYangPTProperties();
        List<YangPTProperties.Xpath> xpathList = yangPTProperties.getXpathList();
        YangPTProperties.Xpath xpath = null;

        for (YangPTProperties.Xpath xpathSetting : xpathList) {
            if (host.contains(xpathSetting.getName().toLowerCase())) {
                xpath = xpathSetting;
                break;
            }
        }
        if (xpath == null) {
            return new ArrayList<>();
        }


        List<Torrent> torrents = new ArrayList<>();
        try {
            // 提前构建基础URL
            UrlBuilder baseUrlBuilder = UrlBuilder.of(host, StandardCharsets.UTF_8);
            UrlBuilder urlBuilder = baseUrlBuilder.addPath("/torrents.php");
            params.forEach(urlBuilder::addQuery);
            String url = urlBuilder.build();

            String result = HttpRequest.get(url)
                    .header(Header.COOKIE, cookie)
                    .timeout(100000)
                    .execute().body();

            // 清洗HTML内容
            Document document = Jsoup.parse(result);
            Elements rows = document.select(xpath.getTorrent().getRowSelector());
            for (Element row : rows) {
                Torrent torrent = new Torrent();

                Element titleElement = row.select(xpath.getTorrent().getTitleSelector()).first();
                if (titleElement != null) {
                    String title = titleElement.text();
                    String href = titleElement.attr("href");
                    torrent.setName(title);
                    torrent.setLink(baseUrlBuilder.addPath(href).build());
                }

                Element subTitleElement = row.select(xpath.getTorrent().getSubTitleSelector()).first();
                if (subTitleElement != null) {
                    String subName = subTitleElement.text();
                    torrent.setSubName(subName);
                }

                Element downloadInnerElement = row.select(xpath.getTorrent().getDownloadInnerSelector()).first();
                if (downloadInnerElement != null) {
                    String downloadHref = downloadInnerElement.parent().attr("href");
                    torrent.setDownloadUrl(baseUrlBuilder.addPath(downloadHref).build());
                }

                List<String> tags = new ArrayList<>();
                Elements tagElements = row.select(xpath.getTorrent().getTagSelector());
                for (Element tagElement : tagElements) {
                    tags.add(tagElement.text());
                }
                torrent.setTags(tags);

                torrents.add(torrent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return torrents;
    }
}
