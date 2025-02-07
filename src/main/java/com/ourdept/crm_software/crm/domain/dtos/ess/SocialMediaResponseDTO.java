package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialMediaResponseDTO {
    private long socialId;
    private String twitterLink;
    private String linkdilenLink;
    private String fbLink;
    private String instaLink;
    private String whatssappNo;
    private String employeeName;  // Display employee's name in response
}
