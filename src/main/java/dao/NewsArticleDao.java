package dao;

import connection.JDBCConnection;
import pojo.NewsArticle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewsArticleDao {

    public void insertArticleAsBatch(String insertQuery, List<NewsArticle> newsArticleList) throws SQLException {
        Connection connection = JDBCConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        connection.setAutoCommit(false);
        newsArticleList.stream().forEach(newsArticle -> {
            String publishedDate = newsArticle.getPublishedDate();
            try {
                writeAsBatch(newsArticle, publishedDate, connection, preparedStatement);
                preparedStatement.addBatch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        int[] ints = preparedStatement.executeBatch();
        System.out.println("Number of records to be inserted : "+ newsArticleList.size());
        System.out.println("Number of records actually inserted : "+Arrays.stream(ints).reduce(Integer::sum).getAsInt());
        connection.commit();
        connection.close();
    }

    private static void writeAsBatch(NewsArticle newsArticle, String publishedDate, Connection connection, PreparedStatement preparedStatement) throws SQLException, ParseException {
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date publishedTimeStamp = (Date)formatter.parse(publishedDate);
        preparedStatement.setString(1, newsArticle.getTitle());
        preparedStatement.setString(2, newsArticle.getLink());
        preparedStatement.setString(3, newsArticle.getImgUrl());
        preparedStatement.setString(4, getCategoriesAsString(newsArticle));
        preparedStatement.setDate(5, new java.sql.Date(publishedTimeStamp.getTime()));
        preparedStatement.setDate(6, new java.sql.Date(new Date().getTime()));
        preparedStatement.setDate(7, new java.sql.Date(new Date().getTime()));
    }

    private static String getCategoriesAsString(NewsArticle newsArticle) {
        if(newsArticle.getCategories()==null){
            return null;
        }
        return newsArticle.getCategories().stream().collect(Collectors.joining(","));
    }
}
