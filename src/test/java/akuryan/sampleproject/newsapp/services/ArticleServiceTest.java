package akuryan.sampleproject.newsapp.services;

import akuryan.sampleproject.newsapp.newsapi.ArticlesRequest;
import akuryan.sampleproject.newsapp.newsapi.ArticlesResponse;
import akuryan.sampleproject.newsapp.model.Article;
import akuryan.sampleproject.newsapp.model.Source;
import akuryan.sampleproject.newsapp.newsapi.NewsApiClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {


    @InjectMocks
    private ArticleService service;

    @Mock
    private NewsApiClient newsApiClient;

    @Test
    public void testGetNews() {
        Pageable pageable = new PageRequest(0, 20);

        int expectedCount = 20;
        ResponseEntity<ArticlesResponse> response = new ResponseEntity<>(createNewsResponse(expectedCount), HttpStatus.OK);
        when(newsApiClient.findArticles(any(Map.class))).thenReturn(response);

        Page<Article> news = service.getNews("java", pageable);

        assertEquals(expectedCount, news.getSize());
    }

    @Test
    public void testCreatePage() {

        Pageable pageable = new PageRequest(0, 20);

        int expectedCount = 20;
        ResponseEntity<ArticlesResponse> response = new ResponseEntity<>(createNewsResponse(expectedCount, 100L), HttpStatus.OK);

        Page<Article> articles = service.createPagedResponse(response.getBody(), pageable);

        assertEquals(articles.getTotalPages(), 5);
        assertEquals(articles.getTotalElements(), 100);
    }

    @Test
    public void testCreateDefaultParameterMap(){
        int page = 0;
        int expectedPage = page + 1;
        int expectedSize = 20;

        Pageable pageable = new PageRequest(page, expectedSize);

        Map<String, Object> actual = service.createDefaultRequestParametersMap(pageable);

        assertEquals(expectedSize, actual.get(ArticlesRequest.PAGE_SIZE));
        assertEquals(expectedPage, actual.get(ArticlesRequest.PAGE));
        assertEquals(ArticlesRequest.ArticlesSortOrder.PUBLISHED_AT.getValue(), actual.get(ArticlesRequest.SORT_BY));
    }

    private ArticlesResponse createNewsResponse(int expectedCount) {
        return createNewsResponse(expectedCount, 20);
    }

    private ArticlesResponse createNewsResponse(int articlesCount, long totalResults) {
        Article[] articles = Stream.generate(ArticleServiceTest::articlesSupplier).limit(articlesCount).toArray(Article[]::new);

        ArticlesResponse response = new ArticlesResponse();
        response.setArticles(articles);
        response.setStatus("ok");
        response.setTotalResults(totalResults);

        return response;
    }

    private static Article articlesSupplier() {
        int id = (int) (Math.random() * 100);
        String title = "Title: " + id;

        Source source = new Source("ID: " + id, "Name: " + id);
        return new Article(title, source);
    }
}