package com.UST.InterviewerForm.service;

import com.UST.InterviewerForm.entity.InterviewEntity;
import com.UST.InterviewerForm.repository.InterviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepo repo;
    public InterviewEntity createDetails(InterviewEntity interview) {
        return repo.save(interview);
    }

    public List<InterviewEntity> getAllInterviewer() {
        return repo.findAll();

    }

    public Optional<InterviewEntity> getInterviewerById(Long Id) {
        return repo.findById(Id);
    }

    public InterviewEntity updateDetails(Long id, InterviewEntity newInterview) {
        Optional<InterviewEntity> optionalInterview = getInterviewerById(id);
        if (optionalInterview.isPresent()) {
            InterviewEntity existingInterview = optionalInterview.get();
            existingInterview.setInterviewerName(newInterview.getInterviewerName());
            existingInterview.setInterviewerRole(newInterview.getInterviewerRole());
            existingInterview.setInterviewerSkills(newInterview.getInterviewerSkills());
            existingInterview.setInterviewerExperience(newInterview.getInterviewerExperience());
            existingInterview.setAvailableTime(newInterview.getAvailableTime());
            return repo.save(existingInterview);
        } else {
            throw new ResourceNotFoundException("Interviewer with id " + id + " not found");
        }
    }
    public void deleteInterview(Long id) {
        repo.deleteById(id);
    }
}