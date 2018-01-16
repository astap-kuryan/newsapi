package akuryan.sampleproject.newsapp.newsapi;

import akuryan.sampleproject.newsapp.model.Article;

public class ArticlesResponse {

    private String status;
    private Long totalResults;
    private Article[] articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }
}
