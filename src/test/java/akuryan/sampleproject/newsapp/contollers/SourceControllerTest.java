package akuryan.sampleproject.newsapp.contollers;

import akuryan.sampleproject.newsapp.model.Source;
import akuryan.sampleproject.newsapp.newsapi.NewsAPIException;
import akuryan.sampleproject.newsapp.services.SourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class SourceControllerTest {


    private MockMvc mvc;

    @InjectMocks
    private SourceController sourceController;

    @Mock
    private SourceService sourceService;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(sourceController).build();
    }

    @Test
    public void testGetSources() throws Exception {
        List<Source> sources = new ArrayList<>();
        when(sourceService.getSources()).thenReturn(sources);
        mvc.perform(get("/sources").accept(MediaType.ALL)).andExpect(status().isOk());

    }

    @Test
    public void testGetSources_exceptionHappened() throws Exception {
        List<Source> sources = new ArrayList<>();
        when(sourceService.getSources()).thenThrow(new NewsAPIException() );
        mvc.perform(get("/sources").accept(MediaType.ALL)).andExpect(status().is5xxServerError());
    }
}