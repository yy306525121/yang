package cn.codeyang.yang.framework.pt.core.api;

import cn.codeyang.yang.framework.pt.config.YangPTProperties;
import cn.codeyang.yang.framework.pt.core.domain.Torrent;
import cn.codeyang.yang.framework.pt.core.domain.UserInfo;
import cn.codeyang.yang.framework.pt.core.utils.SpiderUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
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
    default List<Torrent> fetchTorrents(YangPTProperties.Xpath xpath, String host, String cookie, Map<String, String> params) {

        if (xpath == null) {
            return new ArrayList<>();
        }


        List<Torrent> torrents = new ArrayList<>();
        boolean hasNextPage = true;
        try {
            // 提前构建基础URL
            UrlBuilder baseUrlBuilder = UrlBuilder.of(host, StandardCharsets.UTF_8);
            String baseUrl = baseUrlBuilder.build();
            UrlBuilder urlBuilder = baseUrlBuilder.addPath("/torrents.php");
            String url = urlBuilder.build();
            params.forEach(urlBuilder::addQuery);
            String queryUrl = urlBuilder.build();


            while (hasNextPage) {
                String result = HttpRequest.get(queryUrl)
                        .header(Header.COOKIE, cookie)
                        .timeout(100000)
                        .execute().body();

                // 清洗HTML内容
                Document document = Jsoup.parse(result);
                Elements rows = document.select(xpath.getTorrent().getRowSelector());
                for (Element row : rows) {
                    Torrent torrent = new Torrent();

                    Element titleElement = SpiderUtil.selectFirst(row, xpath.getTorrent().getTitleSelector());
                    if (titleElement != null) {
                        String title = titleElement.text();
                        String href = titleElement.attr("href");
                        torrent.setTitle(title);
                        torrent.setLink(UrlBuilder.of(host, StandardCharsets.UTF_8).addPath(href).build());
                    }

                    String subTitle = SpiderUtil.selectString(row, xpath.getTorrent().getSubTitleSelector());
                    torrent.setSubTitle(subTitle);

                    Element downloadInnerElement = SpiderUtil.selectFirst(row, xpath.getTorrent().getDownloadInnerSelector());
                    if (downloadInnerElement != null) {
                        String downloadHref = downloadInnerElement.parent().attr("href");
                        torrent.setDownloadUrl(UrlBuilder.of(host, StandardCharsets.UTF_8).addPath(downloadHref).build());
                    }

                    List<String> tags = new ArrayList<>();
                    Elements tagElements = row.select(xpath.getTorrent().getTagSelector());
                    for (Element tagElement : tagElements) {
                        tags.add(tagElement.text());
                    }
                    torrent.setTags(tags);

                    String size = SpiderUtil.selectString(row, xpath.getTorrent().getSizeSelector());
                    torrent.setSize(size);

                    Integer seederSize = SpiderUtil.selectInt(row, xpath.getTorrent().getSeederSizeSelector());
                    torrent.setSeederSize(seederSize);

                    Integer leecherSize = SpiderUtil.selectInt(row, xpath.getTorrent().getLeecherSizeSelector());
                    torrent.setLeecherSize(leecherSize);

                    torrents.add(torrent);
                }

                Element nextPageElement = SpiderUtil.selectFirst(document, xpath.getTorrent().getNextPageSelector());
                if (nextPageElement != null) {
                    String href = nextPageElement.attr("href");
                    href = URLUtil.decode(href, StandardCharsets.UTF_8);
                    queryUrl = url + href;
                } else {
                    hasNextPage = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return torrents;
    }

    default UserInfo fetchUserInfo(YangPTProperties.Xpath xpath, String host, String cookie) {
        UserInfo userInfo = new UserInfo();

        String url = UrlBuilder.of(host, StandardCharsets.UTF_8).build();
        String result = HttpRequest.get(url)
                .header(Header.COOKIE, cookie)
                .timeout(100000)
                .execute().body();

        Document document = Jsoup.parse(result);

        Element usernameElement = SpiderUtil.selectFirst(document, xpath.getUserInfo().getUsernameSelector());
        if (usernameElement != null) {
            String username = usernameElement.text().strip();
            userInfo.setUsername(username);
        }

        String uploaded = SpiderUtil.selectNextSiblingText(document, xpath.getUserInfo().getUploadedSelector());
        userInfo.setUploaded(uploaded);

        String downloaded = SpiderUtil.selectNextSiblingText(document, xpath.getUserInfo().getDownloadedSelector());
        userInfo.setDownloaded(downloaded);

        return userInfo;
    }
}
