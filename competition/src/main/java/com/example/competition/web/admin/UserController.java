package com.example.competition.web.admin;

import com.example.competition.po.User;
import com.example.competition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userlist")
    public String userlist(@PageableDefault(size = 10,sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("pages",userService.listUser(pageable));
        return "/admin/userlist";
    }

    //跳转编辑页面
    @GetMapping("/userinfo/{id}/input")
    public String userinfo(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/userinfo";
    }

    //禁用用户
    @GetMapping("userlist/{id}/active")
    public String active(@PageableDefault(size = 10,sort = {"id"},
            direction = Sort.Direction.DESC) User user,Pageable pageable, Model model,Long id){
        //仍提转至userlist页
        userService.updateBanUser(id,user);
        model.addAttribute("pages",userService.listUser(pageable));
        return "admin/userlist";
    }
}
