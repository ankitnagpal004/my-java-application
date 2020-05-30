package com.abc.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.exception.TextSearchFileNotFoundException;
import com.abc.exception.TextSearchFileReadException;
import com.abc.helper.ReadParagraphHelper;
import com.abc.model.SearchWordCount;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchTextService {
	
	@Autowired
    private SearchWordCount searchWordCount;
	
	@Autowired
	ReadParagraphHelper readParagraphHelper;
	
	private static final Logger log = 
		    LogManager.getLogger(SearchTextService.class);
	
	public SearchWordCount searchTextFromFile(String[] searchWords) 
			throws TextSearchFileReadException, TextSearchFileNotFoundException {

		Map<String, Integer> wordCountMap = readParagraphHelper.readParagraphCountWords();
		Map<String, Integer> finalMap = new HashMap<String, Integer>();
		
		for (String token : searchWords) {
			int count = 0;
			if(null != wordCountMap.get(token.toLowerCase()))
			{
				count = wordCountMap.get(token.toLowerCase());
			}
			finalMap.put(token, count);
		}
		
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		list.add(finalMap);
		log.info(list);
		searchWordCount.setCounts(list);
		return searchWordCount;

	}
	
	public String getTopWordCount(int count) 
			throws TextSearchFileReadException, TextSearchFileNotFoundException {
		
		Map<String, Integer> wordCountMap = readParagraphHelper.readParagraphCountWords();
		
		Map<String, Integer> sortedWordCount 
        = wordCountMap.entrySet()
                   .stream()
                   .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                   .collect(Collectors.toMap(Entry::getKey, 
                		   Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));

		StringBuffer topWordCount = new StringBuffer();
		int counter = 0;
		for (Map.Entry<String, Integer> entry: sortedWordCount.entrySet()) {
			topWordCount.append(entry.getKey());
			topWordCount.append("|");
			topWordCount.append(entry.getValue());
			counter ++;
			if(counter == count) {
				break;
			}
			topWordCount.append("\n");
		}
		log.info(topWordCount.toString());
		
		return topWordCount.toString();
	}

}
