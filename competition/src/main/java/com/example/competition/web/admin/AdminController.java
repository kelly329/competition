package com.example.competition.web.admin;


import com.example.competition.po.Admin;
import com.example.competition.po.Type;
import com.example.competition.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/adminuserlist")
    public String userlist(@PageableDefault(size = 10,sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("pages",adminService.listAdmin(pageable));
        return "/admin/adminuserlist";
    }

    //跳转编辑页面
    @GetMapping("/adminuserinfo/{id}/input")
    public String userinfo(@PathVariable Long id, Model model) {
        model.addAttribute("user", adminService.getAdmin(id));
        return "admin/adminuserinfo";
    }

    //修改管理员信息
    @PostMapping("/adminuserinfo/{id}")
    public String editPost(@Valid Admin admin, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        //校验
        Admin admin1 = adminService.getAdminByName(admin.getUsername());
        if (admin1 != null) {
            result.rejectValue("name", "nameError", "用户名不能重复");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        //编辑分类
        Admin a = adminService.updateAdmin(id, admin);
        if (a == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/adminuserlist";
    }

}
