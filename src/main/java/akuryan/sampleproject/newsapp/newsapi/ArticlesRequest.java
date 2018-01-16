package akuryan.sampleproject.newsapp.newsapi;

public class ArticlesRequest {
    public static final String SORT_BY = "sortBy";
    public static final String QUERY = "q";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE = "page";
    public static final String SOURCE = "sources";

    public static String PATH = "/everything";

    public enum ArticlesSortOrder {
        RELEVANCY("relevancy"), POPULARITY("popularity"), PUBLISHED_AT("publishedAt");

        private String value;

        private ArticlesSortOrder(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
