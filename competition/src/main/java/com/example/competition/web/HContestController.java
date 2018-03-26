package com.example.competition.web;

import com.example.competition.dao.ContestRepository;
import com.example.competition.dao.TypeRepository;
import com.example.competition.po.Type;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/web")
public class HContestController {
    @Autowired
    private ContestService contestService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/incontest")
    public String incontest(Model model){
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("contests",contestService.listInContest(6));
        return "web/inContest";
    }

    //校外首页比赛信息
    @GetMapping("/offcontest")
    public String offcontest(Model model){
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("contests",contestService.listOffContest(4));
        return "web/offContest";
    }

    @GetMapping("/incondetail/{id}")
    public String incondetail(@PathVariable Long id, Model model){
        model.addAttribute("contests",contestService.getConest(id));
        return "web/inCondetail";
    }

    @GetMapping("/offcontestdetail/{id}")
    public String offcondetail(@PathVariable Long id, Model model){
        model.addAttribute("contests",contestService.getConest(id));
        return "web/offContestDetail";
    }

    /**
     * 根据typeid筛选比赛信息
     * @param pageable
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/offcontest/types/{id}")
    public String types(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        List<Type> types=typeService.listTypeTop(10000);
        if (id==-1){
            id=types.get(0).getId();
        }
        ContestQuery contestQuery=new ContestQuery();
        contestQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("contests",contestService.listContest(pageable,contestQuery).getContent());
        model.addAttribute("activeTypeId",id);
        return "web/offContest";
    }

    @GetMapping("/inconlst")
    public String inconlst(@PageableDefault(sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",contestService.listInContest(2));
        return "web/inConlst";
    }

}
