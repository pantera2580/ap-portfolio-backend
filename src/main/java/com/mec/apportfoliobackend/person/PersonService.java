package com.mec.apportfoliobackend.person;

import com.mec.apportfoliobackend.config.ModelMapper;
import com.mec.apportfoliobackend.exception.PersonNotFoundException;
import com.mec.apportfoliobackend.security.user.IUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonService implements IPersonService {
    private final IPersonRepository personRepository;
    private final IUserRepository userRepository;

    public PersonService(IPersonRepository personRepository, IUserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PersonResponse save(PersonRequest personRequest, String id) {
        if(!userRepository.existsById(UUID.fromString(id))) throw new UsernameNotFoundException("Username not found");
        Person person = ModelMapper.personRequestToEntity(personRequest, id);
        personRepository.save(person);
        return ModelMapper.entityToPersonResponse(person);
    }

    @Override
    public PersonResponse update(PersonRequest personRequest, String id) throws PersonNotFoundException {
        Person person = personRepository.findById(UUID.fromString(id)).orElseThrow(()-> new PersonNotFoundException("Person not found"));
        ModelMapper.updatePersonalData(person, personRequest);
        personRepository.save(person);
        return ModelMapper.entityToPersonResponse(person);
    }

    @Override
    public boolean existById(String id) {
        return personRepository.existsById(UUID.fromString(id));
    }

    @Override
    public PersonResponse findById(String id) throws PersonNotFoundException {
        Person person = personRepository.findById(UUID.fromString(id)).orElseThrow(()->new PersonNotFoundException("Person not found"));
        return ModelMapper.entityToPersonResponse(person);
    }

}
