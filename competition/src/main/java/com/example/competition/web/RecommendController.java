package com.example.competition.web;

import com.example.competition.po.Contest;
import com.example.competition.po.Recommend;
import com.example.competition.po.Type;
import com.example.competition.service.ContestService;
import com.example.competition.service.RecommendService;
import com.example.competition.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/web")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/recommend")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("recommend",new Recommend());
        return "web/recommend";
    }

    @PostMapping("recommend")
    public String post(Recommend recommend, RedirectAttributes attributes){
        //存入推荐
        Recommend r = recommendService.saveRecommend(recommend);
        if (r == null) {
            attributes.addFlashAttribute("message", "推荐失败");
        } else {
            attributes.addFlashAttribute("message", "推荐成功");
        }
        return "redirect:/web/index";
    }
}
