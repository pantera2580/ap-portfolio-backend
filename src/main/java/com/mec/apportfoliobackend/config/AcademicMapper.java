package com.mec.apportfoliobackend.config;

import com.mec.apportfoliobackend.academic.Academic;
import com.mec.apportfoliobackend.academic.AcademicRequest;
import com.mec.apportfoliobackend.academic.AcademicResponse;

import java.util.ArrayList;
import java.util.List;

public class AcademicMapper {
    public static List<AcademicResponse> academicListToAcademicResponseList(List<Academic> academicList) {
        List<AcademicResponse> academicResponseList = new ArrayList<>();
        for (Academic academic : academicList) {
            AcademicResponse academicResponse = createAcademicResponse(academic);
            academicResponseList.add(academicResponse);
        }
        return academicResponseList;
    }

    public static AcademicResponse academicToAcademicResponse(Academic academic) {
        return createAcademicResponse(academic);
    }

    public static AcademicResponse createAcademicResponse(Academic academic) {
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

    public static Academic academicRequestToAcademic(AcademicRequest academicRequest) {
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