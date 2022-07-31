package com.mec.apportfoliobackend.experience;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ExperienceResponse {
    private String id;
    private String company;
    private String job;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate finishDate;
    private String description;
    private String personId;

    public ExperienceResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
