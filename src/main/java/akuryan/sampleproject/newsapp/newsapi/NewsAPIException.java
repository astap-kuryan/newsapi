package akuryan.sampleproject.newsapp.newsapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Failed to get response from newsapi")
public class NewsAPIException extends RuntimeException {
}
