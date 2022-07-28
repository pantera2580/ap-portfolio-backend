package com.mec.apportfoliobackend.config;

import com.mec.apportfoliobackend.experience.Experience;
import com.mec.apportfoliobackend.experience.ExperienceRequest;
import com.mec.apportfoliobackend.experience.ExperienceResponse;

import java.util.ArrayList;
import java.util.List;

public class ExperienceMapper {
    public static List<ExperienceResponse> experienceListToExperienceResponseList(List<Experience> experienceList) {
        List<ExperienceResponse> experienceResponseList = new ArrayList<>();
        for (Experience experience : experienceList) {
            ExperienceResponse experienceResponse = createExperienceResponse(experience);
            experienceResponseList.add(experienceResponse);
        }
        return experienceResponseList;
    }

    public static ExperienceResponse createExperienceResponse(Experience experience) {
        ExperienceResponse experienceResponse = new ExperienceResponse();
        experienceResponse.setId(experience.getId().toString());
        experienceResponse.setDescription(experience.getDescription());
        experienceResponse.setInitialDate(experience.getInitialDate());
        experienceResponse.setFinishDate(experience.getFinishDate());
        experienceResponse.setCompany(experience.getCompany());
        experienceResponse.setJob(experience.getJob());
        experienceResponse.setPersonId(experience.getPerson().getId().toString());
        return experienceResponse;
    }

    public static Experience experienceRequestToExperience(ExperienceRequest experienceRequest) {
        Experience experience = new Experience();
        updateExperienceData(experience, experienceRequest);
        return experience;
    }

    public static void updateExperienceData(Experience experience, ExperienceRequest experienceRequest) {
        experience.setJob(experienceRequest.getJob());
        experience.setCompany(experienceRequest.getCompany());
        experience.setInitialDate(experienceRequest.getInitialDate());
        experience.setFinishDate(experienceRequest.getFinishDate());
        experience.setDescription(experienceRequest.getDescription());
    }

    public static ExperienceResponse experienceToExperienceResponse(Experience experience) {
        return createExperienceResponse(experience);
    }
}