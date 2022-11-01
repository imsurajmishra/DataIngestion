import core.RSSFeedLogic;
import dao.NewsArticleDao;
import pojo.NewsArticle;
import com.sun.syndication.io.FeedException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class RSSFeedIngestion {

    public static final String feedUrl = "https://cloudblog.withgoogle.com/rss/";

    private static final String INSERT_QUERY = "INSERT INTO NEWS_ARTICLES (title, link, image_url, " +
            "category, published_date, create_timestamp, update_timestamp)" +
            "VALUES (?,?,?,?,?,?,?)";

    private static RSSFeedLogic rssFeedLogic = new RSSFeedLogic();
    private static NewsArticleDao newsArticleDao = new NewsArticleDao();

    public static void main(String[] args) throws IOException, FeedException, SQLException {
        List<NewsArticle> newsArticleList = rssFeedLogic.getNewsArticlesFromRSSFeed(feedUrl);
        newsArticleDao.insertArticleAsBatch(INSERT_QUERY,newsArticleList);
    }
}
