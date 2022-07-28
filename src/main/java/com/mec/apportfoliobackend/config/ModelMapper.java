package com.mec.apportfoliobackend.config;

import com.mec.apportfoliobackend.academic.Academic;
import com.mec.apportfoliobackend.academic.AcademicRequest;
import com.mec.apportfoliobackend.academic.AcademicResponse;
import com.mec.apportfoliobackend.person.Person;
import com.mec.apportfoliobackend.person.PersonRequest;
import com.mec.apportfoliobackend.person.PersonResponse;
import com.mec.apportfoliobackend.security.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
    public static List<AcademicResponse> academicListToAcademicResponseList(List<Academic> academicList){
        List<AcademicResponse> academicResponseList = new ArrayList<>();
        for(Academic academic : academicList){
            AcademicResponse academicResponse = createAcademicResponse(academic);
            academicResponseList.add(academicResponse);
        }
        return academicResponseList;
    }
    public static AcademicResponse academicToAcademicResponse(Academic academic){
        return createAcademicResponse(academic);
    }

    private static AcademicResponse createAcademicResponse(Academic academic) {
        AcademicResponse academicResponse = new AcademicResponse();
        academicResponse.setId(academic.getId().toString());
        academicResponse.setDescription(academic.getDescription());
        academicResponse.setOrganization(academic.getOrganization());
        academicResponse.setTitle(academic.getTitle());
        academicResponse.setInitialDate(academic.getInitialDate());
        academicResponse.setFinishDate(academic.getFinishDate());
        academicResponse.setPersonId(academic.getPerson().getId().toString());
        return academicResponse;
    }
    public static Academic academicRequestToAcademic(AcademicRequest academicRequest){
        Academic academic = new Academic();
        updateAcademicData(academic, academicRequest);
        return academic;
    }
    public static void updateAcademicData(Academic academic, AcademicRequest academicRequest) {
        academic.setDescription(academicRequest.getDescription());
        academic.setOrganization(academicRequest.getOrganization());
        academic.setTitle(academicRequest.getTitle());
        academic.setInitialDate(academicRequest.getInitialDate());
        academic.setFinishDate(academicRequest.getFinishDate());
    }
}
