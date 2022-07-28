package com.mec.apportfoliobackend.skills;

import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.exception.SkillNotFoundException;

import java.util.List;

public interface ISkillsService {    List<SkillsResponse> findAllByPersonId(String personId) throws PersonNotFoundException;
    SkillsResponse save(SkillsRequest academicRequest, String personId) throws PersonNotFoundException;
    SkillsResponse update(SkillsRequest academicRequest, String academicId) throws SkillNotFoundException;
    void delete(String skillsId) throws SkillNotFoundException;

}
