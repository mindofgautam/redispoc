/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.searchapi.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * @author gautamb
 *
 */
public class FileVo {

	private static final String SPLIT_STRING = "_";

	private static final String FILES_STRING = "files:";

	private String filePath;

	private String lineContent;

	private Long lineNumber;

	public FileVo(String filePath, String lineContent, Long lineNumber) {
		this.filePath = filePath;
		this.lineContent = lineContent;
		this.lineNumber = lineNumber;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the lineContent
	 */
	public String getLineContent() {
		return lineContent;
	}

	/**
	 * @param lineContent
	 *            the lineContent to set
	 */
	public void setLineContent(String lineContent) {
		this.lineContent = lineContent;
	}

	/**
	 * @return the lineNumber
	 */
	public Long getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber
	 *            the lineNumber to set
	 */
	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getFilePathWithLineNumber() {
		return FILES_STRING + filePath + SPLIT_STRING + lineNumber;
	}

	public static String[] stripFilePathAndLineNumber(String text) {
		String[] values = new String[2];
		if (StringUtils.startsWith(text, FILES_STRING)) {
			String filePathWithLineNumber = text.substring(FILES_STRING.length());
			String[] splitValue = filePathWithLineNumber.split(SPLIT_STRING);
			values[0] = "";
			for (int i = 0; i < splitValue.length - 1; i++) {
				values[0] += splitValue[i];
			}
			values[1] = splitValue[splitValue.length - 1];
		}
		return values;
	}
}
