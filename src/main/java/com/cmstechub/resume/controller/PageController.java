package com.cmstechub.resume.controller;

import com.cmstechub.resume.entity.ResumeProfile;
import com.cmstechub.resume.repository.ResumeProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final ResumeProfileRepository repository;

    public PageController(ResumeProfileRepository repository) {
        this.repository = repository;
    }

    private ResumeProfile profile() {
        return repository.findAll().stream().findFirst().orElse(new ResumeProfile());
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("profile", profile());
        return "about";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("profile", profile());
        return "projects";
    }

    @GetMapping("/skills")
    public String skills(Model model) {
        model.addAttribute("profile", profile());
        return "skills";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("profile", profile());
        return "contact";
    }
}
