/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.searchapi.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.gautam.searchapi.redis.RedisHandler;
import com.gautam.searchapi.vo.SearchResultVO;
import com.gautam.searchapi.vo.SearchResultsVO;

import redis.clients.jedis.Tuple;

/**
 * @author gautamb
 *
 */

@Service
public class SearchService {

	RedisHandler redisHandler = new RedisHandler();

	/**
	 * 
	 * @param searchResult
	 * @return
	 */
	public SearchResultsVO getTopSearchResult(String searchResult) {
		SearchResultsVO searchResultsVo = new SearchResultsVO();
		ArrayList<SearchResultVO> results = new ArrayList<SearchResultVO>();
		Set<Tuple> resultTuple = redisHandler.getTopValues(searchResult);

		for (Tuple tuple : resultTuple) {
			SearchResultVO result = new SearchResultVO();
			results.add(result);

			String string = new String(tuple.getBinaryElement());

			result.setOccurcaseCount(String.valueOf((int) tuple.getScore()));
			String element = string;
			String[] stripFilePathAndLineNumber = com.gautam.searchapi.vo.FileVo.stripFilePathAndLineNumber(element);
			result.setFilePath(stripFilePathAndLineNumber[0]);
			result.setFileContent(redisHandler.get(string));
			result.setLineNumber(stripFilePathAndLineNumber[1]);
		}
		searchResultsVo.setResults(results);
		searchResultsVo.setTotalHits(resultTuple.size());
		searchResultsVo.setSearchedWord(searchResult);
		return searchResultsVo;
	}

}
