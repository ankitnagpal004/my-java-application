package com.abc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abc.model.SearchWordCount;
import com.abc.model.SearchWords;

@Configuration
public class AppConfig {
	
	@Bean
	public SearchWordCount searchWordCount() {
		return new SearchWordCount();
	}
	
	@Bean
	public SearchWords searchWords() {
		return new SearchWords();
	}
}
