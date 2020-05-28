package com.abc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.helper.ReadParagraphHelper;
import com.abc.model.SearchWordCount;

@Service
public class SearchTextService {
	
	@Autowired
    private SearchWordCount searchWordCount;
	
	@Autowired
	ReadParagraphHelper readParagraphHelper;
	
	public SearchWordCount searchTextFromFile(String[] searchWords) throws IOException {

		System.out.println(searchWords);
		
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
		
		System.out.println(finalMap);
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		list.add(finalMap);
		System.out.println(list);
		searchWordCount.setCounts(list);
		return searchWordCount;

	}
	
	public String getTopWordCount(int count) throws IOException {
		
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
		
		return topWordCount.toString();
	}

}
