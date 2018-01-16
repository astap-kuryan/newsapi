package akuryan.sampleproject.newsapp.newsapi;

import akuryan.sampleproject.newsapp.model.Source;

public class SourcesResponse {

    private String status;
    private Source[] sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Source[] getSources() {
        return sources;
    }

    public void setSources(Source[] sources) {
        this.sources = sources;
    }
}
