package akuryan.sampleproject.newsapp.contollers;

import akuryan.sampleproject.newsapp.model.Source;
import akuryan.sampleproject.newsapp.services.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @RequestMapping("/sources")

    public List<Source> getSources(){
        return sourceService.getSources();
    }
}