package com.mec.apportfoliobackend.experience;

import com.mec.apportfoliobackend.config.ModelMapper;
import com.mec.apportfoliobackend.exception.ExperienceNotFoundException;
import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.person.IPersonRepository;
import com.mec.apportfoliobackend.person.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExperienceService implements IExperienceService{
    private final IExperienceRepository experienceRepository;
    private final IPersonRepository personRepository;

    public ExperienceService(IExperienceRepository experienceRepository, IPersonRepository personRepository) {
        this.experienceRepository = experienceRepository;
        this.personRepository = personRepository;
    }
    @Override
    public List<ExperienceResponse> findAllByPersonId(String personId) throws PersonNotFoundException {
        Person person = new Person();
        person.setId(UUID.fromString(personId));
        List<Experience> experienceList = experienceRepository.findByPerson(person).orElseThrow(()->new PersonNotFoundException("Person not found"));
        return ModelMapper.experienceListToExperienceResponseList(experienceList);
    }
    @Override
    public ExperienceResponse save(ExperienceRequest experienceRequest, String personId) throws PersonNotFoundException {
        if(!personRepository.existsById(UUID.fromString(personId))) throw new PersonNotFoundException("Person not found");
        Person person = new Person();
        person.setId(UUID.fromString(personId));
        Experience experience = ModelMapper.experienceRequestToExperience(experienceRequest);
        experience.setPerson(person);
        experienceRepository.save(experience);
        return ModelMapper.experienceToExperienceResponse(experience);
    }
    @Override
    public ExperienceResponse update(ExperienceRequest experienceRequest, String experienceId) throws ExperienceNotFoundException {
        Experience experience = experienceRepository.findById(UUID.fromString(experienceId)).orElseThrow(()->new ExperienceNotFoundException("Experience not found"));
        ModelMapper.updateExperienceData(experience, experienceRequest);
        experienceRepository.save(experience);
        return ModelMapper.experienceToExperienceResponse(experience);
    }
    @Override
    public void delete(String experienceId) throws ExperienceNotFoundException {
        if(!experienceRepository.existsById(UUID.fromString(experienceId))) throw new ExperienceNotFoundException("Experience not found");
        experienceRepository.deleteById(UUID.fromString(experienceId));
    }
}
