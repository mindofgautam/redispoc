/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.searchapi.vo;

/**
 * @author gautamb
 *
 */
public class SearchResultVO {

	private String filePath;

	private String occurcaseCount;

	private String fileContent;

	private String lineNumber;

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the occurcaseCount
	 */
	public String getOccurcaseCount() {
		return occurcaseCount;
	}

	/**
	 * @param occurcaseCount the occurcaseCount to set
	 */
	public void setOccurcaseCount(String occurcaseCount) {
		this.occurcaseCount = occurcaseCount;
	}

	/**
	 * @return the fileContent
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * @param fileContent the fileContent to set
	 */
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	/**
	 * @return the lineNumber
	 */
	public String getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

}
