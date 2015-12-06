/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.searchapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gautam.searchapi.service.SearchService;
import com.gautam.searchapi.vo.SearchResultsVO;

/**
 * @author gautamb
 *
 */
@RestController
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SearchEndpoint {

	@Autowired
	SearchService serarchService;

	@RequestMapping(value = "/search/{searchTerm}", method = RequestMethod.GET)
	public SearchResultsVO search(@PathVariable String searchTerm, Model model) {
		return serarchService.getTopSearchResult(searchTerm);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SearchEndpoint.class, args);
	}

}
