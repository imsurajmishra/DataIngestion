package core;

import mapper.RSSFeedMapper;
import pojo.NewsArticle;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static mapper.RSSFeedMapper.*;

public class RSSFeedLogic {

    public  List<NewsArticle> getNewsArticlesFromRSSFeed(String feedUrl) throws FeedException, IOException {
        URL feedSource = new URL(feedUrl);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));
        Iterator itr = feed.getEntries().iterator();
        List<NewsArticle> results = new ArrayList<>();
        while (itr.hasNext()) {
            SyndEntry syndEntry = (SyndEntry) itr.next();
            results.add(mapToArticle(syndEntry));
        }
        return results;
    }
}
