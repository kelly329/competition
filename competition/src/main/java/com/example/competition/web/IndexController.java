package com.example.competition.web;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private ContestService contestService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("contests",contestService.listContestTop(3));
        model.addAttribute("hotcontests",contestService.listHotContestTop(2));
        model.addAttribute("recommends",contestService.listRecommendContestTop(5));
        return "index";
    }

    /**
     * 根据比赛标题／简介 搜索
     * @param pageable
     * @param query
     * @param model
     * @return
     */
    @PostMapping("/web/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String query, Model model) {
        model.addAttribute("page",contestService.listContest("%" + query + "%", pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("query", query);
        return "/web/search";
    }

    /**
     * 根据typeid获取对应的比赛列表
     * @param pageable
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        List<Type> types=typeService.listTypeTop(10000);
        if (id==-1){
            id=types.get(0).getId();
        }
        ContestQuery contestQuery=new ContestQuery();
        contestQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("contests",contestService.listContest(pageable,contestQuery).getContent());
        model.addAttribute("activeTypeId",id);
        return "index";
    }


}
