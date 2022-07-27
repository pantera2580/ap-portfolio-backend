package com.mec.apportfoliobackend.config;

import com.mec.apportfoliobackend.person.Person;
import com.mec.apportfoliobackend.person.PersonRequest;
import com.mec.apportfoliobackend.person.PersonResponse;
import com.mec.apportfoliobackend.security.user.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ModelMapper {
    public static Person personRequestToEntity(PersonRequest personRequest){
        return createPerson(personRequest);
    }

    public static Person personRequestToEntity(PersonRequest personRequest, String id){
        Person person = createPerson(personRequest);
        User user = new User();
        user.setId(UUID.fromString(id));
        person.setUser(user);
        return person;
    }
    public static PersonResponse entityToPersonResponse(Person person){
        PersonResponse personResponse = new PersonResponse();
        personResponse.setName(person.getName());
        personResponse.setLastname(person.getLastname());
        personResponse.setAddress(person.getAddress());
        personResponse.setEmail(person.getEmail());
        personResponse.setPhone(person.getPhone());
        personResponse.setGithubUrl(person.getGithubUrl());
        personResponse.setLinkedinUrl(person.getLinkedinUrl());
        personResponse.setId(person.getId().toString());
        personResponse.setProfileImage(person.getProfileImage());
        return personResponse;
    }
    public static Person createPerson(PersonRequest personRequest) {
        Person person = new Person();
        updatePersonalData(person, personRequest);
        return person;
    }

    public static void updatePersonalData(Person person, PersonRequest personRequest) {
        person.setName(personRequest.getName());
        person.setLastname(personRequest.getLastname());
        person.setAddress(personRequest.getAddress());
        person.setEmail(personRequest.getEmail());
        person.setGithubUrl(personRequest.getGithubUrl());
        person.setLinkedinUrl(personRequest.getLinkedinUrl());
        person.setPhone(personRequest.getPhone());
        person.setProfileImage(personRequest.getProfileImage());
    }

}
