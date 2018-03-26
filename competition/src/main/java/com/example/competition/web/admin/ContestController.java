package com.example.competition.web.admin;

import com.example.competition.po.Contest;
import com.example.competition.service.ContestService;
import com.example.competition.service.TypeService;
import com.example.competition.vo.ContestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/contestlist")
    public String contestlist(@PageableDefault(size = 2,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable, ContestQuery contest, Model model){
        model.addAttribute("page",contestService.listContest( pageable, contest));
        model.addAttribute("types",typeService.listType());
        return "admin/contestlist";
    }

    @PostMapping("/contestlist/search")
    public String search(@PageableDefault(size = 2,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable, ContestQuery contest, Model model){
        model.addAttribute("page",contestService.listContest( pageable, contest));
        return "admin/contestlist::conLst";
    }

    @GetMapping("/contestlist/{id}/input")
    public String editinput(@PathVariable Long id, Model model) {
        Contest contest = contestService.getConest(id);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("contest", contest);

        return "/admin/addcontest";
    }

    @GetMapping("/contestlist/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("contest",new Contest());
        return "admin/addcontest";
    }

    @PostMapping("/contestlist")
    public String post(Contest contest, RedirectAttributes attributes){
        contest.setType(typeService.getType(contest.getType().getId()));
        Contest c;
        if (contest.getId() == null) {
            c = contestService.saveContest(contest);
        } else {
            c = contestService.updateContest(contest.getId(), contest);
        }
        if (c == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/contestlist";
    }
}
