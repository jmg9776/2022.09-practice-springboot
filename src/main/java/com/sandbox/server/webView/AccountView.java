package com.sandbox.server.webView;

import com.sandbox.server.model.param.AccountParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class AccountView {
    @GetMapping("/login")
    public String loginView(@ModelAttribute AccountParam accountParam, @RequestParam(value="error", required = false) String error, Model model){
        model.addAttribute("error", error);
        if (error != null) model.addAttribute("exception", "로그인 실패");
        return "login";
    }
    @GetMapping("/info")
    public @ResponseBody String info(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername() + " Hello!";
    }
}
