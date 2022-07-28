package com.mec.apportfoliobackend.config;

import com.mec.apportfoliobackend.skills.Skills;
import com.mec.apportfoliobackend.skills.SkillsRequest;
import com.mec.apportfoliobackend.skills.SkillsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillsMapper {
    public static List<SkillsResponse> skillsListToSkillsResponseList(List<Skills> skillsList) {
        List<SkillsResponse> skillsResponseList = new ArrayList<>();
        for (Skills skills : skillsList) {
            SkillsResponse skillsResponse = createSkillsResponse(skills);
            skillsResponseList.add(skillsResponse);
        }
        return skillsResponseList;
    }

    public static SkillsResponse createSkillsResponse(Skills skills) {
        SkillsResponse skillsResponse = new SkillsResponse();
        skillsResponse.setId(skills.getId().toString());
        skillsResponse.setDescription(skills.getDescription());
        skillsResponse.setTitle(skills.getTitle());
        skillsResponse.setPersonId(skills.getPerson().getId().toString());
        return skillsResponse;
    }

    public static Skills skillsRequestToSkills(SkillsRequest skillsRequest) {
        Skills skills = new Skills();
        updateSkillsData(skills, skillsRequest);
        return skills;
    }

    public static void updateSkillsData(Skills skills, SkillsRequest skillsRequest) {
        skills.setTitle(skillsRequest.getTitle());
        skills.setDescription(skillsRequest.getDescription());
    }

    public static SkillsResponse skillsToSkillsResponse(Skills skills) {
        return createSkillsResponse(skills);
    }
}
