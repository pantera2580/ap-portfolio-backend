package com.mec.apportfoliobackend.experience;

import com.mec.apportfoliobackend.exception.ExperienceNotFoundException;
import com.mec.apportfoliobackend.exception.PersonNotFoundException;

import java.util.List;

public interface IExperienceService {
    List<ExperienceResponse> findAllByPersonId(String personId) throws PersonNotFoundException;
    ExperienceResponse save(ExperienceRequest academicRequest, String personId) throws PersonNotFoundException;
    ExperienceResponse update(ExperienceRequest academicRequest, String academicId) throws ExperienceNotFoundException;
    void delete(String experienceId) throws ExperienceNotFoundException;
}
