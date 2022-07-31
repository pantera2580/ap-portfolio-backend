package com.mec.apportfoliobackend.skills;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

public class SkillsRequest {
    private String title;
    @Positive
    @Max(value = 100)
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
