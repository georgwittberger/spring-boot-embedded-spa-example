package io.github.georgwittberger.springbootembeddedspaexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SinglePageController {

    private final String contextPath;

    public SinglePageController(@Value("${server.servlet.context-path:}") String contextPath) {
        this.contextPath = contextPath;
    }

    @GetMapping(path = { "/", "/{name:^(?!api|css|js).+}/**" }, produces = MediaType.TEXT_HTML_VALUE)
    public String getSinglePage(Model model) {
        model.addAttribute("contextPath", contextPath);
        return "index";
    }

}
