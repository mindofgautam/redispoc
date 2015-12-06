/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.fileindexer.filescanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;

/**
 * @author gautamb
 *
 */
public class FileScanner {

	protected Logger logger = Logger.getLogger(getClass());

	Map<String, LineIterator> iteratorMap = new HashMap<String, LineIterator>();

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public List<String> getLines(String path) throws IOException {

		LineIterator currentIterator = iteratorMap.get(path);

		if (currentIterator == null) {
			currentIterator = FileUtils.lineIterator(new File(path));
			iteratorMap.put(path, currentIterator);
		}

		List<String> lines = new ArrayList<String>();

		while (currentIterator.hasNext()) {
			String line = currentIterator.nextLine();
			lines.add(line);
		}

		return lines;
	}

}
