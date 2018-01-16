package akuryan.sampleproject.newsapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AppConfiguration {

    @Value("${newsapi.url}")
    private String NEWS_API_URL;


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.rootUri(NEWS_API_URL).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }

}
