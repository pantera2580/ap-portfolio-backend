package com.mec.apportfoliobackend.experience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService implements IExperienceService{
    @Autowired
    private IExperienceRepository experienceRepository;
}
