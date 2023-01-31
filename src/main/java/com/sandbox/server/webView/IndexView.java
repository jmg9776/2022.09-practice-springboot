package com.sandbox.server.webView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexView {

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
