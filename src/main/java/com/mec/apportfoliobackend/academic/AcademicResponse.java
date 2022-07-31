package com.mec.apportfoliobackend.academic;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AcademicResponse {
    private String id;
    private String organization;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate finishDate;
    private String description;
    private String personId;

    public AcademicResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
