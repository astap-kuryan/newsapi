package akuryan.sampleproject.newsapp.services;

import akuryan.sampleproject.newsapp.newsapi.NewsApiClient;
import akuryan.sampleproject.newsapp.newsapi.SourcesRequest;
import akuryan.sampleproject.newsapp.newsapi.SourcesResponse;
import akuryan.sampleproject.newsapp.model.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SourceServiceTest {

    @InjectMocks
    private SourceService service;

    @Mock
    private NewsApiClient newsApiClient;

    @Test
    public void testGetSources() {
        int expectedSourceCount = 2;

        SourcesResponse response = createSourcesResponse(expectedSourceCount);
        ResponseEntity<SourcesResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

        when(newsApiClient.findSources(new HashMap<>())).thenReturn(responseEntity);

        List<Source> actual = service.getSources();

        assertEquals(expectedSourceCount, actual.size());

        verify(newsApiClient).findSources(new HashMap<>());
    }

    @Test
    public void testGetSources_zeroSources(){
        int expectedSourceCount = 0;

        SourcesResponse response = createSourcesResponse(expectedSourceCount);
        ResponseEntity<SourcesResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

        when(newsApiClient.findSources(new HashMap<>())).thenReturn(responseEntity);

        List<Source> actual = service.getSources();

        assertEquals(expectedSourceCount, actual.size());
    }

    private SourcesResponse createSourcesResponse(int sourceCount) {
        Source[] sources = Stream.generate(SourceServiceTest::sourceSupplier).limit(sourceCount).toArray(Source[]::new);

        SourcesResponse response = new SourcesResponse();
        response.setStatus("ok");
        response.setSources(sources);

        return response;
    }

    private static Source sourceSupplier() {
        int id = (int) (Math.random()*100);
        return new Source("id" + id, "name " + id);
    }
}