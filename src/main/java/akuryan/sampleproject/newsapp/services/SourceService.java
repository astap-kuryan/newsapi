package akuryan.sampleproject.newsapp.services;

import akuryan.sampleproject.newsapp.newsapi.NewsApiClient;
import akuryan.sampleproject.newsapp.newsapi.SourcesResponse;
import akuryan.sampleproject.newsapp.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class SourceService {

    @Autowired
    private NewsApiClient newsApiClient;

    public List<Source> getSources() {
        ResponseEntity<SourcesResponse> response = newsApiClient.findSources(new HashMap<>());
        return Arrays.asList(response.getBody().getSources());
    }
}
