package com.mec.apportfoliobackend.person;

import com.mec.apportfoliobackend.exception.PersonNotFoundException;

public interface IPersonService {
    PersonResponse save(PersonRequest personRequest, String id);
    PersonResponse update(PersonRequest personRequest, String id) throws PersonNotFoundException;
    boolean existById(String id);
    PersonResponse findById(String id) throws PersonNotFoundException;
}
