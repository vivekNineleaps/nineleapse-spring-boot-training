package com.nineleaps.springboot.training.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class I18nController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@GetMapping("/i18n-1")
	public String getMessageI18NFormat() {
		return "Hello I18N";
	}
	
	@GetMapping("/i18n-2")
	public String getMessageI18NFormat2(@RequestHeader(name="Accept-Language",required=false) String locale ) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}

	
	@GetMapping("/i18n-3")
	public String getMessageI18NFormat3(@RequestHeader(name="Accept-Language",required=false) String locale ) {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
