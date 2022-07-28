package com.mec.apportfoliobackend.academic;

import com.mec.apportfoliobackend.config.AcademicMapper;
import com.mec.apportfoliobackend.exception.AcademicNotFoundException;
import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.person.IPersonRepository;
import com.mec.apportfoliobackend.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AcademicService implements IAcademicService{
    private IAcademicRepository academicRepository;
    private final IPersonRepository personRepository;

    public AcademicService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public AcademicService(IAcademicRepository academicRepository, IPersonRepository personRepository) {
        this.academicRepository = academicRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<AcademicResponse> findAllByPersonId(String personId) throws PersonNotFoundException {
        Person person = new Person();
        person.setId(UUID.fromString(personId));
        List<Academic> academicList = academicRepository.findByPerson(person).orElseThrow(()->new PersonNotFoundException("Person not found"));
        return AcademicMapper.academicListToAcademicResponseList(academicList);
    }

    @Override
    public AcademicResponse save(AcademicRequest academicRequest, String personId) throws PersonNotFoundException {
        if(!personRepository.existsById(UUID.fromString(personId))) throw new PersonNotFoundException("Person not found");
        Person person = new Person();
        person.setId(UUID.fromString(personId));
        Academic academic = AcademicMapper.academicRequestToAcademic(academicRequest);
        academic.setPerson(person);
        academicRepository.save(academic);
        return AcademicMapper.academicToAcademicResponse(academic);
    }

    @Override
    public AcademicResponse update(AcademicRequest academicRequest, String academicId) throws AcademicNotFoundException {
        Academic academic = academicRepository.findById(UUID.fromString(academicId)).orElseThrow(()->new AcademicNotFoundException("Academic not found"));
        AcademicMapper.updateAcademicData(academic, academicRequest);
        academicRepository.save(academic);
        return AcademicMapper.academicToAcademicResponse(academic);
    }

    @Override
    public void delete(String academicId) throws AcademicNotFoundException {
        if(!academicRepository.existsById(UUID.fromString(academicId))) throw new AcademicNotFoundException("Academic not found");
        academicRepository.deleteById(UUID.fromString(academicId));
    }
}
