package com.backendtest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Jobs {
    private String id;
    private String type;
    private String url;
    private String createdAt;
    private String company;
    private String companyUrl;
    private String location;
    private String title;
    private String description;
    private String howToApply;
    private String companyLogo;
}
