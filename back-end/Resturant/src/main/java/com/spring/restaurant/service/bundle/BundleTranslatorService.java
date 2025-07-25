package com.spring.restaurant.service.bundle;

import com.spring.restaurant.service.dto.BundleMessage.BundleMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BundleTranslatorService {

    private static ResourceBundleMessageSource messageSource;

    public BundleTranslatorService(@Qualifier("messages") ResourceBundleMessageSource messageSource) {

        this.messageSource = messageSource;
    }

    public static BundleMessage getBundleMessageinEnglishAndArabic(String key) {

        return new BundleMessage(
                messageSource.getMessage(key, null, new Locale("en") ),
                messageSource.getMessage(key, null,  new Locale("ar"))
        );


    }
}
