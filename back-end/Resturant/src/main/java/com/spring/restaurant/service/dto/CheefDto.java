package com.spring.restaurant.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheefDto extends BaseDto {

    private String facebook_link;

    private String instagram_link;

    private String twitter_link;

    private String specialty;
}
