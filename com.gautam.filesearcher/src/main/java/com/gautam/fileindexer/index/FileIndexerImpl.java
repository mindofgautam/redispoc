/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.fileindexer.index;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.gautam.fileindexer.filescanner.FileScanner;
import com.gautam.fileindexer.redis.RedisHandler;
import com.gautam.fileindexer.vo.FileVo;

/**
 * @author gautamb
 *
 */
public class FileIndexerImpl implements FileIndexer {

	protected Logger logger = Logger.getLogger(getClass());

	private static final String PATH_DIRECTORY = "index.directory.path";

	private static String baseDirectory;

	private RedisHandler redisHandler;

	private static Properties properties;

	static {
		properties = new Properties();
		InputStream inputStream = null;
		ClassPathResource propsResource = new ClassPathResource("conf/indexer.properties");
		try {
			inputStream = propsResource.getInputStream();
			if (inputStream != null) {
				properties.load(inputStream);
				baseDirectory = properties.getProperty(PATH_DIRECTORY);
			}
		} catch (Exception e) {
			String errorString = "Error while reading the property file";
			throw new RuntimeException(errorString, e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public void index() throws Exception {
		redisHandler = new RedisHandler();
		redisHandler.flushAll();

		String[] filePathArray = getAllFilesInPath(baseDirectory, null);
		FileScanner fileScanner = new FileScanner();
		List<String> lines = new ArrayList<String>();

		int filesCompletedCount = 0;
		for (String filePath : filePathArray) {
			lines = fileScanner.getLines(filePath);
			indexLines(filePath, lines);
			logger.info("Indexing completed " + ++filesCompletedCount + "/" + filePathArray.length);
		}
	}

	/**
	 * @param string
	 * @return
	 * @throws Exception
	 */
	private String[] getAllFilesInPath(String directoryPath, String[] extensions) throws Exception {
		File directory = new File(directoryPath);
		List<File> files;
		try {
			files = (List<File>) FileUtils.listFiles(directory, extensions, true);
		} catch (Exception exception) {
			logger.error("Invalid directory specified in indexer.properties");
			throw new Exception("Invalid directory specified in indexer.properties");
		}
		String[] filePath = new String[files.size()];
		logger.info("Total Files Found :" + filePath.length);

		for (int iteration = 0; iteration < files.size(); iteration++) {
			File file = files.get(iteration);
			logger.info("File Path :" + file.getCanonicalPath());
			filePath[iteration] = file.getCanonicalPath();
		}
		return filePath;
	}

	private void indexLines(String filePath, List<String> lines) {

		long lineNumber = 1l;

		for (String line : lines) {

			// Indexes all the lines witht the file
			FileVo fileLineVo = new FileVo(filePath, line, lineNumber);
			redisHandler.put(fileLineVo.getFilePathWithLineNumber(), fileLineVo.getLineContent());

			// TODO: add more stop-words.
			String[] stopWords = line.split("\\s");

			String previouslyIndexedString = "";

			// Each word is and its combinations are stored.
			for (int i = 0; i < stopWords.length; i++) {

				String word = StringUtils.substring(line, StringUtils.indexOf(line, stopWords[i]));
				if (StringUtils.isNotBlank(word)) {

					String keyword = "";
					for (Character character : word.trim().toCharArray()) {
						keyword += character;
						if (!StringUtils.equalsIgnoreCase(previouslyIndexedString.trim(), keyword.trim())) {
							redisHandler.pushToSet(keyword.trim(), fileLineVo.getFilePathWithLineNumber());
							previouslyIndexedString = keyword.trim();
							// lower case is also indexed
							if (!StringUtils.equals(keyword.toLowerCase().trim(), keyword)) {
								redisHandler.pushToSet(keyword.toLowerCase().trim(),
										fileLineVo.getFilePathWithLineNumber());
							}
						}
					}
				}
			}
			lineNumber++;

		}
	}
}