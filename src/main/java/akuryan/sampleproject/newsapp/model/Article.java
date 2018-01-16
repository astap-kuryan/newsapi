package akuryan.sampleproject.newsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {

    private String title;
    private Source source;

    public Article() {
    }

    public Article(String title, Source source) {
        this.title = title;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
