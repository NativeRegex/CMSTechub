package com.cmstechub.resume.controller;

import com.cmstechub.resume.entity.ResumeProfile;
import com.cmstechub.resume.repository.ResumeProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeController {

    private final ResumeProfileRepository repository;

    public ResumeController(ResumeProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {
        ResumeProfile profile = repository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
        model.addAttribute("profile", profile);
        return "index";
    }
}
