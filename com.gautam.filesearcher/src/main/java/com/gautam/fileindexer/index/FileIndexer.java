/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.fileindexer.index;

/**
 * @author gautamb
 *
 */
public interface FileIndexer {

	/**
	 * indexes all the documents
	 * 
	 * @throws Exception
	 */
	public void index() throws Exception;
}