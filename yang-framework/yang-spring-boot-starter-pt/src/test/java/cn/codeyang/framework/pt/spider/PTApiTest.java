package cn.codeyang.framework.pt.spider;

import cn.codeyang.yang.framework.pt.config.YangPTProperties;
import cn.codeyang.yang.framework.pt.core.domain.Torrent;
import cn.codeyang.yang.framework.pt.core.spider.impl.HDHomeApi;
import cn.codeyang.yang.framework.pt.core.spider.impl.SpringSundayApi;
import cn.codeyang.yang.framework.test.core.ut.BaseMockitoUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class PTApiTest extends BaseMockitoUnitTest {
    @Mock
    private YangPTProperties properties;

    private List<YangPTProperties.Xpath> xpathList = new ArrayList<>();

    @BeforeEach
    public void before() {

        YangPTProperties.Xpath.Torrent hdhomeTorrent = new YangPTProperties.Xpath.Torrent();
        hdhomeTorrent.setRowSelector(".torrents tbody tr:not(:first-child)");
        hdhomeTorrent.setTitleSelector("table.torrentname > tbody > tr > td:first-child > a");
        hdhomeTorrent.setSubTitleSelector("table.torrentname > tbody > tr > td:first-child > span:last-child");
        hdhomeTorrent.setDownloadInnerSelector("img.download");
        hdhomeTorrent.setTagSelector("table.torrentname > tbody > tr > td:first-child > span.tags");

        YangPTProperties.Xpath hdhomeXpath = new YangPTProperties.Xpath();
        hdhomeXpath.setName("hdhome");
        hdhomeXpath.setTorrent(hdhomeTorrent);

        xpathList.add(hdhomeXpath);
    }

    @Test
    public void HDHomeTorrentSearchTest() {
        when(properties.getXpathList()).thenReturn(xpathList);


        HDHomeApi api = new HDHomeApi(properties);

        Map<String, String> params = new HashMap<>();
        params.put("search", "变形金刚");

        List<Torrent> torrents = api.fetchTorrents("https://hdhome.org", "", params);
        assertTrue(!torrents.isEmpty());
    }

    @Test
    public void springSundayTorrentSearchTest() {
        SpringSundayApi api = new SpringSundayApi(properties);
        Map<String, String> params = new HashMap<>();
        params.put("search", "变形金刚");

        List<Torrent> torrents = api.fetchTorrents("https://springsunday.net", "", params);
        assertTrue(!torrents.isEmpty());
    }
}
