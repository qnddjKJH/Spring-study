package com.n2014758030.main.controller;

import com.n2014758030.main.domain.Basic;
import com.n2014758030.main.service.BasicService;
import com.n2014758030.main.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class BasicController {

    private BasicService basicService;
    private ProfileService profileService;

    public BasicController(BasicService basicService, ProfileService profileService) {
        this.basicService = basicService;
        this.profileService = profileService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("basicList", basicService.findBasicByAll());
        model.addAttribute("profileList", profileService.findProfileByAll());
        return "index";
    }

    @GetMapping("/basic/new")
    public String newBasic(Basic basic) {
        return "new";
    }

    @PostMapping("/addBasic")
    public String addBasic(Basic basic, Model model) {
        basicService.save(basic);
        model.addAttribute("basicList", basicService.findBasicByAll());
        model.addAttribute("profileList", profileService.findProfileByAll());
        return "index";
    }

    @GetMapping("/basic/{idx}")
    public String readBasic(@PathVariable Long idx, Model model) {
        model.addAttribute("basic", basicService.findBasicByIdx(idx));
        return "item";
    }

    @GetMapping("/basic/{idx}/update")
    public String updateBasic(@PathVariable Long idx, Model model) {
        model.addAttribute("basic", basicService.findBasicByIdx(idx));
        return "update";
    }

    @PostMapping("/basic/update/check/{idx}")
    public String checkBasic(@PathVariable Long idx, Basic updateBasic, Model model) {
        Basic savedBasic = basicService.findBasicByIdx(idx);

        savedBasic.setName(updateBasic.getName());
        savedBasic.setlabel(updateBasic.getLabel());
        savedBasic.setEmail(updateBasic.getEmail());
        savedBasic.setPhone(updateBasic.getPhone());

        basicService.updateBasic(savedBasic);

        model.addAttribute("basic", basicService.findBasicByIdx(savedBasic.getIdx()));
        return "item";
    }

    @PostMapping("/basic/delete")
    public String deleteBasic(@RequestParam(value = "deleteBasicsIdx", required = false) List<Long> deleteBasicsIdx,
                              Model model, HttpServletResponse response) throws IOException {
        if(deleteBasicsIdx == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('There is nothing to delete.'); location.href='/';</script>");
            out.flush();
        } else {
            for (Long i : deleteBasicsIdx) {
                basicService.deleteBasic(basicService.findBasicByIdx(i));
            }
        }

        model.addAttribute("basicList", basicService.findBasicByAll());
        model.addAttribute("profileList", profileService.findProfileByAll());

        return "index";
    }
}
