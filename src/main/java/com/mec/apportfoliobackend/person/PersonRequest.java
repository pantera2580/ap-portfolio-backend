package com.mec.apportfoliobackend.person;

import javax.validation.constraints.NotBlank;

public class PersonRequest {
    @NotBlank(message = "lastname is required")
    private String lastname;
    @NotBlank(message = "name is required")
    private String name;
    private String profileImage;
    private String address;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "phone numbre is required")
    private String phone;
    private String linkedinUrl;
    private String githubUrl;

    public PersonRequest() {
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

    public void setAddress(String address) {
        this.address = address;
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

    public void setPhone(String phone) {
        this.phone = phone;
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
}
