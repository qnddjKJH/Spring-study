package com.n2014758030.main.controller;

import com.n2014758030.main.domain.Profile;
import com.n2014758030.main.service.BasicService;
import com.n2014758030.main.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProfileController {

    private BasicService basicService;
    private ProfileService profileService;

    public ProfileController(BasicService basicService, ProfileService profileService) {
        this.basicService = basicService;
        this.profileService = profileService;
    }

    @PostMapping("/addProfile")
    public String addProfile(Profile profile, Model model) {
        profile.setCreateDate(LocalDateTime.now());
        profileService.save(profile);
        model.addAttribute("basicList", basicService.findBasicByAll());
        model.addAttribute("profileList", profileService.findProfileByAll());
        return "index";
    }

    @GetMapping("/profile/new")
    public String newProfile(Profile profile) {
        return "new";
    }

    @GetMapping("/profile/{idx}")
    public String readProfile(@PathVariable Long idx, Model model) {
        model.addAttribute("profile", profileService.findProfileByIdx(idx));
        return "item";
    }

    @GetMapping("/profile/{idx}/update")
    public String updateProfile(@PathVariable Long idx, Model model) {
        model.addAttribute("profile", profileService.findProfileByIdx(idx));
        return "update";
    }

    @PostMapping("/profile/update/check/{idx}")
    public String checkProfile(@PathVariable Long idx, Profile updateProfile, Model model) {

        Profile savedProfile = profileService.findProfileByIdx(idx);

        savedProfile.setNetwork(updateProfile.getNetwork());
        savedProfile.setUserName(updateProfile.getUserName());
        savedProfile.setUrl(updateProfile.getUrl());

        profileService.updateProfile(savedProfile);

        System.out.println(savedProfile.getIdx());

        model.addAttribute("profile", profileService.findProfileByIdx(savedProfile.getIdx()));
        return "item";
    }

    @PostMapping("/profile/delete")
    public String deleteProfile(@RequestParam(value = "deleteProfileIdx", required = false) List<Long> deleteProfileIdx,
                                Model model, HttpServletResponse response) throws IOException {
        if(deleteProfileIdx == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('There is nothing to delete.'); location.href='/';</script>");
            out.flush();
        } else {
            for (Long i : deleteProfileIdx) {
                profileService.deleteProfile(profileService.findProfileByIdx(i));
            }
        }
        model.addAttribute("basicList", basicService.findBasicByAll());
        model.addAttribute("profileList", profileService.findProfileByAll());

        return "index";
    }
}
