package akuryan.sampleproject.newsapp.newsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class NewsApiClient {

    protected static String API_KEY_PARAM = "apiKey";

    @Value("${newsapi.apiKey}")
    public String API_KEY_VALUE;


    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<SourcesResponse> findSources(Map<String, Object> parameters){
        return executeQuery(SourcesRequest.PATH, parameters, SourcesResponse.class);
    }

    public ResponseEntity<ArticlesResponse> findArticles(Map<String, Object> parameters){
        return executeQuery(ArticlesRequest.PATH, parameters, ArticlesResponse.class);
    }

    protected <T> ResponseEntity<T> executeQuery(String path, Map<String, Object> parameters, Class<T> type) {
        parameters.put(API_KEY_PARAM, API_KEY_VALUE);
        String uri = buildURI(path, parameters);

        ResponseEntity<T> response = restTemplate.getForEntity(uri, type);

        if (!response.getStatusCode().is2xxSuccessful()){
            throw new NewsAPIException();
        }
        return response;
    }

    private String buildURI(String path, Map<String, Object> parameters) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path);
        parameters.forEach((k, v) -> builder.queryParam(k, v));
        return builder.build().toUriString();
    }
}
