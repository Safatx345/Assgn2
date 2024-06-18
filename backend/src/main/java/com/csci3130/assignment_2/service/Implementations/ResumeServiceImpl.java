package com.csci3130.assignment_2.service.Implementations;

import com.csci3130.assignment_2.model.Resume;
import com.csci3130.assignment_2.repository.ResumeRepository;
import com.csci3130.assignment_2.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeRepository resumeRepository;

    public static class ResumeNotFoundException extends RuntimeException {
        public ResumeNotFoundException(String message) {
            super(message);
        }
    }

    @Override
    public String createResume(Resume resume) {
        resumeRepository.save(resume);
        return "Resume created";
    }

    @Override
    public List<Resume> getResume(String role) {
        List<Resume> resumes = resumeRepository.findByRole(role);
        if (resumes.isEmpty()) {
            throw new ResumeNotFoundException("No resumes found for role: " + role);
        }
        return resumes;
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
    @Override
    public List<Resume> getResumesByExperience(int minYearsOfExperience) {
        List<Resume> allResumes = resumeRepository.findAll();
        List<Resume> filteredResumes = new ArrayList<>();
        for (Resume resume : allResumes) {
            String yearsOfExperienceStr = resume.getYearsOfExperience().replaceAll("\\D+", "");
            int yearsOfExperience = yearsOfExperienceStr.isEmpty() ? 0 : Integer.parseInt(yearsOfExperienceStr);
            if (yearsOfExperience >= minYearsOfExperience) {
                filteredResumes.add(resume);
            }
        }
        return filteredResumes;
    }


}
