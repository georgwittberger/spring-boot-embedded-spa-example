package io.github.georgwittberger.springbootembeddedspaexample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping(path = "/api/text", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getText() {
        return "Hello World";
    }

}
