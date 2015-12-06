/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.searchapi.vo;

import java.util.List;

/**
 * @author gautamb
 *
 */
public class SearchResultsVO {

	private List<SearchResultVO> results;

	private Integer totalHits;

	private String searchedWord;

	/**
	 * @return the results
	 */
	public List<SearchResultVO> getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(List<SearchResultVO> results) {
		this.results = results;
	}

	/**
	 * @return the searchedWord
	 */
	public String getSearchedWord() {
		return searchedWord;
	}

	/**
	 * @param searchedWord
	 *            the searchedWord to set
	 */
	public void setSearchedWord(String searchedWord) {
		this.searchedWord = searchedWord;
	}

	/**
	 * @return the totalHits
	 */
	public Integer getTotalHits() {
		return totalHits;
	}

	/**
	 * @param totalHits
	 *            the totalHits to set
	 */
	public void setTotalHits(Integer totalHits) {
		this.totalHits = totalHits;
	}

}
