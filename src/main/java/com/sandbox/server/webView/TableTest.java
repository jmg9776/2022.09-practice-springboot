package com.sandbox.server.webView;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TableTest {
    @GetMapping("/table")
    public String home(){
        return "tables";
    }


}
