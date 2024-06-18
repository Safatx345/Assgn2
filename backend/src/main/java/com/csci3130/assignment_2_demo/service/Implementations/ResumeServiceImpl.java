package com.csci3130.assignment_2_demo.service.Implementations;

import com.csci3130.assignment_2_demo.model.Resume;
import com.csci3130.assignment_2_demo.repository.ResumeRepository;
import com.csci3130.assignment_2_demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeRepository resumeRepository;

    @Override
    public String createResume(Resume resume) {
        resumeRepository.save(resume);
        return "Resume created";
    }

    @Override
    public List<Resume> getResume(String role) {
        return resumeRepository.findByRole(role);
    }

    @Override
    public List<String> getQualifications(String role) {
        List<Resume> resumes = resumeRepository.findByRole(role);
        List<String> qualifications = new ArrayList<>();
        for (Resume resume : resumes) {
            qualifications.add(resume.getQualifications());
        }
        return qualifications;

    }

    @Override
    public List<String> getWorkExperience(String role) {
        List<Resume> resumes = resumeRepository.findByRole(role);
        List<String> workExperiences = new ArrayList<>();
        for (Resume resume : resumes) {
            workExperiences.add(resume.getWorkExperience());
        }
        return workExperiences;
    }


}
