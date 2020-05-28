package com.abc.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component
public class ReadParagraphHelper {
	
	public Map<String, Integer> readParagraphCountWords() throws IOException {
		File file = new File(getClass().getClassLoader().getResource("Paragraph.txt").getFile());
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		String line = null;
		Map<String, Integer> finalMap = new HashMap<String, Integer>();
		
		while ((line = br.readLine()) != null) {
			String[] words = line.split("[\\s,.]+");
			for (String word : words) {				
				if(finalMap.get(word.toLowerCase())!=null){
					finalMap.put(word.toLowerCase(), finalMap.get(word.toLowerCase())+1);
				}else{
					finalMap.put(word.toLowerCase(), 1);
				}
			}
		}
		br.close();
		
		return finalMap;
	}
}
