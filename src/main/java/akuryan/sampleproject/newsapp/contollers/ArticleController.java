package akuryan.sampleproject.newsapp.contollers;

import akuryan.sampleproject.newsapp.model.Article;
import akuryan.sampleproject.newsapp.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/articles")
    public Page<Article> getArticles(String sourceId, Pageable pageable){
        return articleService.getNews("java", sourceId, pageable);
    }
}
