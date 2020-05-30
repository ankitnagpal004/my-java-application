package com.abc.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.abc.exception.TextSearchFileNotFoundException;
import com.abc.exception.TextSearchFileReadException;

@Component
public class ReadParagraphHelper {
	
	@Value("${user.file.name}")
	private String filename;
	
	public Map<String, Integer> readParagraphCountWords() 
			throws TextSearchFileNotFoundException, TextSearchFileReadException {
		URL url = getClass().getClassLoader().getResource(filename);
		if (url == null) {
			throw new TextSearchFileNotFoundException("File not present at the path specified");
		}
		File file = new File(url.getFile());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			throw new TextSearchFileNotFoundException("Contact administrator, file is not present");
		}

		String line = null;
		Map<String, Integer> finalMap = new HashMap<String, Integer>();
		
		try {
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
		} catch (IOException e) {
			throw new TextSearchFileReadException("Not able to read the file");
		}		
		return finalMap;
	}
}
