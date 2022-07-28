package com.mec.apportfoliobackend.academic;

import com.mec.apportfoliobackend.exception.AcademicNotFoundException;
import com.mec.apportfoliobackend.exception.PersonNotFoundException;

import java.util.List;

public interface IAcademicService {
    List<AcademicResponse> findAllByPersonId(String personId) throws PersonNotFoundException;
    AcademicResponse save(AcademicRequest academicRequest, String personId) throws PersonNotFoundException;
    AcademicResponse update(AcademicRequest academicRequest, String academicId) throws AcademicNotFoundException;
    void delete(String academicId) throws AcademicNotFoundException;

}
