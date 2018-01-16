package akuryan.sampleproject.newsapp.newsapi;

import akuryan.sampleproject.newsapp.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsApiClientTest {

    @InjectMocks
    private NewsApiClient client;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testExecuteQuery_verifyApiKeyAdded() {
        Map<String, Object> parameters = new HashMap<>();

        ResponseEntity<ArticlesResponse> response = new ResponseEntity<>(new ArticlesResponse(), HttpStatus.OK);
        when(restTemplate.getForEntity(any(String.class), eq(ArticlesResponse.class))).thenReturn(response);
        
        ResponseEntity<ArticlesResponse> actual = client.executeQuery(ArticlesRequest.PATH, parameters, ArticlesResponse.class);

        assertTrue(parameters.containsKey(NewsApiClient.API_KEY_PARAM));
    }


    @Test(expected = RuntimeException.class)
    public void testExecuteQuery_throwsExceptionWhenResponseFailed() {
        Map<String, Object> parameters = new HashMap<>();

        ResponseEntity<ArticlesResponse> response = new ResponseEntity<>(new ArticlesResponse(), HttpStatus.FORBIDDEN);
        when(restTemplate.getForEntity(any(String.class), eq(ArticlesResponse.class))).thenReturn(response);

        client.executeQuery(ArticlesRequest.PATH, parameters, ArticlesResponse.class);
    }
}