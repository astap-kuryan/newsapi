package akuryan.sampleproject.newsapp.services;

import akuryan.sampleproject.newsapp.newsapi.ArticlesRequest;
import akuryan.sampleproject.newsapp.newsapi.ArticlesResponse;
import akuryan.sampleproject.newsapp.newsapi.NewsApiClient;
import akuryan.sampleproject.newsapp.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Autowired
    private NewsApiClient newsApiClient;

    public Page<Article> getNews(String query, Pageable pageable){
        Map<String, Object> requestParameters = createDefaultRequestParametersMap(pageable);
        requestParameters.put(ArticlesRequest.QUERY, query);

        ResponseEntity<ArticlesResponse> response = newsApiClient.findArticles(requestParameters);

        return createPagedResponse(response.getBody(), pageable);
    }

    public Page<Article> getNews(String query, String source, Pageable pageable) {
        Map<String, Object> requestParameters = createDefaultRequestParametersMap(pageable);
        requestParameters.put(ArticlesRequest.QUERY, query);
        requestParameters.put(ArticlesRequest.SOURCE, source);

        ResponseEntity<ArticlesResponse> response = newsApiClient.findArticles(requestParameters);

        return createPagedResponse(response.getBody(), pageable);
    }

    protected Map<String, Object> createDefaultRequestParametersMap(Pageable pageable) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ArticlesRequest.SORT_BY, ArticlesRequest.ArticlesSortOrder.PUBLISHED_AT.getValue());
        parameters.put(ArticlesRequest.PAGE_SIZE, pageable.getPageSize());
        parameters.put(ArticlesRequest.PAGE, getAsNewsApiPageIndex(pageable.getPageNumber()));
        return parameters;
    }

    protected Page<Article> createPagedResponse(ArticlesResponse response, Pageable pageable) {
        List<Article> articles = Arrays.asList(response.getArticles());
        return new PageImpl<>(articles, pageable, response.getTotalResults());
    }

    private int getAsNewsApiPageIndex(int pageNumber) {
        return pageNumber + 1;
    }
}
