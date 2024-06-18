package com.csci3130.assignment_2_demo.controller;


import com.csci3130.assignment_2_demo.model.Resume;
import com.csci3130.assignment_2_demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @PostMapping("/create")
    public void createResume(@RequestBody Resume resume) {
        resumeService.createResume(resume);
    }

    @GetMapping("/get/{role}")
    public List<Resume> getResume(@PathVariable String role) {
        return resumeService.getResume(role);
    }

    @GetMapping("/get/qualifications/{role}")
    public List<String> getQualifications(@PathVariable String role) {
        return resumeService.getQualifications(role);
    }

    @GetMapping("/get/work-experience/{role}")
    public List<String> getWorkExperience(@PathVariable String role) {
        return resumeService.getWorkExperience(role);
    }

}
