package mapper;

import com.sun.syndication.feed.synd.SyndEntry;
import pojo.NewsArticle;

public class RSSFeedMapper {

    /**
     * Map to Article
     * @param syndEntry
     */
    public static NewsArticle mapToArticle(SyndEntry syndEntry) {
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setTitle(syndEntry.getTitle());
        newsArticle.setPublishedDate(syndEntry.getPublishedDate().toString());
        newsArticle.setImgUrl("");
        newsArticle.setLink(syndEntry.getLink());
        return newsArticle;
    }
}
