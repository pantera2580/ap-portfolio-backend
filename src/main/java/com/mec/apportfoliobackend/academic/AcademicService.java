package com.mec.apportfoliobackend.academic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicService implements IAcademicService{
    @Autowired
    private IAcademicRepository academicRepository;
}
