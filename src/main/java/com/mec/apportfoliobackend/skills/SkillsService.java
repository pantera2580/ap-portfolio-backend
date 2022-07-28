package com.mec.apportfoliobackend.skills;

import com.mec.apportfoliobackend.config.SkillsMapper;
import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.exception.SkillNotFoundException;
import com.mec.apportfoliobackend.person.IPersonRepository;
import com.mec.apportfoliobackend.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SkillsService implements ISkillsService {
    private final ISkillsRepository skillsRepository;
    private final IPersonRepository personRepository;
    @Autowired
    public SkillsService(ISkillsRepository skillsRepository, IPersonRepository personRepository) {
        this.skillsRepository = skillsRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<SkillsResponse> findAllByPersonId(String personId) throws PersonNotFoundException {
        Person person = new Person();
        person.setId(UUID.fromString(personId));
        List<Skills> skillsList = skillsRepository.findByPerson(person).orElseThrow(()->new PersonNotFoundException("Person not found"));
        return SkillsMapper.skillsListToSkillsResponseList(skillsList);
    }

    @Override
    public SkillsResponse save(SkillsRequest skillsRequest, String personId) throws PersonNotFoundException {
        if(!personRepository.existsById(UUID.fromString(personId))) throw new PersonNotFoundException("Person not found");
        Person person = new Person();
        person.setId(UUID.fromString(personId));
        Skills skills = SkillsMapper.skillsRequestToSkills(skillsRequest);
        skills.setPerson(person);
        skillsRepository.save(skills);
        return SkillsMapper.skillsToSkillsResponse(skills);
    }

    @Override
    public SkillsResponse update(SkillsRequest skillsRequest, String skillsId) throws SkillNotFoundException {
        Skills skills = skillsRepository.findById(UUID.fromString(skillsId)).orElseThrow(()->new SkillNotFoundException("Skills not found"));
        SkillsMapper.updateSkillsData(skills, skillsRequest);
        skillsRepository.save(skills);
        return SkillsMapper.skillsToSkillsResponse(skills);
    }

    @Override
    public void delete(String skillsId) throws SkillNotFoundException {
        if(!skillsRepository.existsById(UUID.fromString(skillsId))) throw new SkillNotFoundException("Skills not found");
        skillsRepository.deleteById(UUID.fromString(skillsId));
    }
}
