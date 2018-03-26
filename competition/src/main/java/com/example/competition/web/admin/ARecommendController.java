package com.example.competition.web.admin;

import com.example.competition.po.Recommend;
import com.example.competition.service.RecommendService;
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
public class ARecommendController {
    @Autowired
    private RecommendService recommendService;

    //获取推荐列表
    @GetMapping("/recommendlst")
    public String types(@PageableDefault(size = 2,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",recommendService.listRecommend(pageable));
        return "admin/recommendlst";
    }

    @GetMapping("/recommendetail/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", recommendService.getRecommend(id));
        return "admin/types-input";
    }

    @GetMapping("/recommedlst/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        recommendService.deleteRecommend(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/recommendlst";
    }

    @PostMapping("/recommendetail/{id}")
    public String editPost(@Valid Recommend recommend, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        //编辑分类
        Recommend r = recommendService.updateRecommed(id, recommend);
        if (r == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/recommendlst";
    }

}
