package com.github.rsallar.cineapi.scraping;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rsallar.cineapi.scraping.ElPais;

public class ElPaisIntTest {
	Logger logger = LoggerFactory.getLogger(ElPaisIntTest.class);
	ElPais elPais;
	
	@Before
	public void before(){
		elPais = new ElPais();
	}

	@Test
	public void test1(){
		//logger.info("{}", elPais.getCinemas().size());
		
		elPais.getInfo();
	}

}
