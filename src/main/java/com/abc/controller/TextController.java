package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.exception.TextSearchFileNotFoundException;
import com.abc.exception.TextSearchFileReadException;
import com.abc.exception.TextSearchValidationException;
import com.abc.model.SearchWordCount;
import com.abc.model.SearchWords;
import com.abc.service.SearchTextService;

@RestController
@RequestMapping(path = "/counter-api")
public class TextController 
{    
	@Autowired
	SearchTextService searchTextService;
	
	@PostMapping(path="/search")
	public SearchWordCount searchTextCount(@RequestBody SearchWords searchWords) 
			throws TextSearchFileReadException, TextSearchFileNotFoundException {
		if(searchWords.getSearchText() == null || searchWords.getSearchText().length == 0) {
			throw new TextSearchValidationException("Please share valid list of searchword");
		}
		return searchTextService.searchTextFromFile(searchWords.getSearchText());
	}
	
	@GetMapping(path="/top/{count}")
	public String listUser(@PathVariable("count") Integer count)
			throws TextSearchFileReadException, TextSearchFileNotFoundException {
		if(count <= 0) {
			throw new TextSearchValidationException("Please share valid count");
		}
		return searchTextService.getTopWordCount(count);
	}
}
