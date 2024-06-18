package com.csci3130.assignment_2.controller;

import com.csci3130.assignment_2.model.Resume;
import com.csci3130.assignment_2.service.ResumeService;
import com.csci3130.assignment_2.service.Implementations.ResumeServiceImpl.ResumeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @PostMapping("/create")
    public ResponseEntity<String> createResume(@RequestBody Resume resume) {
        return new ResponseEntity<>(resumeService.createResume(resume), HttpStatus.CREATED);
    }

    @GetMapping("/get/{role}")
    public ResponseEntity<List<Resume>> getResume(@PathVariable String role) {
        List<Resume> resumes = resumeService.getResume(role);
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

    @GetMapping("/get/qualifications/{role}")
    public ResponseEntity<List<String>> getQualifications(@PathVariable String role) {
        List<String> qualifications = resumeService.getQualifications(role);
        return new ResponseEntity<>(qualifications, HttpStatus.OK);
    }

    @GetMapping("/get/work-experience/{role}")
    public ResponseEntity<List<String>> getWorkExperience(@PathVariable String role) {
        List<String> workExperiences = resumeService.getWorkExperience(role);
        return new ResponseEntity<>(workExperiences, HttpStatus.OK);
    }

    @GetMapping("/get/by-experience/{minYearsOfExperience}")
    public ResponseEntity<List<Resume>> getResumesByExperience(@PathVariable int minYearsOfExperience) {
        List<Resume> resumes = resumeService.getResumesByExperience(minYearsOfExperience);
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<String> handleResumeNotFoundException(ResumeNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
