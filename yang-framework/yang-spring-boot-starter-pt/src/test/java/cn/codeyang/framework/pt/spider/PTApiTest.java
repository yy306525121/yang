package cn.codeyang.framework.pt.spider;

import cn.codeyang.yang.framework.pt.config.YangPTProperties;
import cn.codeyang.yang.framework.pt.core.domain.Torrent;
import cn.codeyang.yang.framework.pt.core.api.impl.HDHomeApi;
import cn.codeyang.yang.framework.pt.core.api.impl.SpringSundayApi;
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
        YangPTProperties.Xpath.Torrent springSundayTorrent = new YangPTProperties.Xpath.Torrent();
        springSundayTorrent.setRowSelector("table.torrents tbody tr:not(:first-child)");
        springSundayTorrent.setTitleSelector("table.torrentname > tbody > tr > td:first-child > div.torrent-title > a");
        springSundayTorrent.setSubTitleSelector("table.torrentname > tbody > tr > td:first-child > div.torrent-smalldescr > span:last-child");
        springSundayTorrent.setTagSelector("table.torrentname > tbody > tr > td:first-child span.torrent-tag");
        springSundayTorrent.setDownloadInnerSelector("span.bi-download");
        springSundayTorrent.setSizeSelector("> td:eq(4)");
        springSundayTorrent.setSeederSizeSelector("> td:eq(5)");
        springSundayTorrent.setLeecherSizeSelector("> td:eq(6)");
        springSundayTorrent.setNextPageSelector("p.nav > a:contains(下一页)");

        YangPTProperties.Xpath springSundayXpath = new YangPTProperties.Xpath();
        springSundayXpath.setName("springsunday");
        springSundayXpath.setTorrent(springSundayTorrent);


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
        xpathList.add(springSundayXpath);

        when(properties.getXpathList()).thenReturn(xpathList);
    }

    @Test
    public void HDHomeTorrentSearchTest() {

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
