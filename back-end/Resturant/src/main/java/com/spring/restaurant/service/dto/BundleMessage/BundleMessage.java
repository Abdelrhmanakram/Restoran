package com.spring.restaurant.service.dto.BundleMessage;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BundleMessage {

    @JsonProperty("message_en")
    private String messageEn;
    @JsonProperty("message_ar")
    private String messageAr;

    public BundleMessage(String messageEn) {
        this.messageEn = messageEn;
    }

    public BundleMessage(String messageEn, String messageAr) {

        this.messageEn = messageEn;
        this.messageAr = messageAr;

    }
}
