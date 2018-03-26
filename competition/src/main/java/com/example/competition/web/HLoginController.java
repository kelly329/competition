package com.example.competition.web;

import com.example.competition.po.Type;
import com.example.competition.po.User;
import com.example.competition.service.UserService;
import com.example.competition.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/web")
public class HLoginController {
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String loginPage(){
        return "web/login";
    }

    @PostMapping("web/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes)
    {
        User user = userService.checkUser(username,password);

        if (user != null) {
            user.setPassword(null);

            session.setAttribute("user", user);
            return "index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/web/login";
        }

    }

    @GetMapping("/register")
    public String registerPage(){
        return "web/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, RedirectAttributes attributes){
        //校验
        User newuser = userService.getUserByName(user.getUserName());
        if (newuser != null) {
            result.rejectValue("name", "nameError", "用户名已存在");
        }
        if(result.hasErrors()){
            return "web/register";
        }
        User u = userService.saveUser(user);
        if (u == null) {
            attributes.addFlashAttribute("message", "注册失败");
        } else {
            attributes.addFlashAttribute("message", "注册成功");
        }
        return "redirect:/web/login";
    }


}
