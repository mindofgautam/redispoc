/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.fileindexer.process;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gautam.fileindexer.index.FileIndexerImpl;

/**
 * @author gautamb
 *
 */
public class FullIndexerProcess {

	protected Logger logger = Logger.getLogger(getClass());

	private static ApplicationContext applicationContext;

	private FileIndexerImpl fileIndexer;

	private static void initalizeApplicationContext() {
		if (applicationContext == null) {
			if (applicationContext == null) {
				applicationContext = new ClassPathXmlApplicationContext(getApplicationContextConfigurationFiles());
			}
		}
	}

	private static String[] getApplicationContextConfigurationFiles() {
		return new String[] { "spring/applicationContext-fileseacher.xml" };
	}

	public static void main(String[] args) throws Exception {
		initalizeApplicationContext();
		FullIndexerProcess indexProcess = applicationContext.getBean(FullIndexerProcess.class);
		indexProcess.index();
	}

	public void index() throws Exception {
		fileIndexer.index();
	}

	/**
	 * @return the fileIndexer
	 */
	public FileIndexerImpl getFileIndexer() {
		return fileIndexer;
	}

	/**
	 * @param fileIndexer
	 *            the fileIndexer to set
	 */
	public void setFileIndexer(FileIndexerImpl fileIndexer) {
		this.fileIndexer = fileIndexer;
	}

}
