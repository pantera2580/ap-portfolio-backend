package com.mec.apportfoliobackend.skills;

public class SkillsRequest {
    private String title;
    private Long percentage;

    public SkillsRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }
}
