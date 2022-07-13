package com.mec.apportfoliobackend.person;

import com.mec.apportfoliobackend.academic.Academic;
import com.mec.apportfoliobackend.experience.Experience;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;
    private String lastname;
    private String name;
    private String profileImage;
    private String address;
    private String email;
    private String phone;
    private String linkedinUrl;
    private String githubUrl;
    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE})
    private Set<Experience> experiences;
    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE})
    private Set<Academic> academicFormations;
    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE})
    private Set<Academic> skills;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;
    public Person() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public Set<Academic> getAcademicFormations() {
        return academicFormations;
    }

    public void setAcademicFormations(Set<Academic> academicFormations) {
        this.academicFormations = academicFormations;
    }

    public Set<Academic> getSkills() {
        return skills;
    }

    public void setSkills(Set<Academic> skills) {
        this.skills = skills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String number) {
        this.phone = number;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
