package com.meli.mutants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.meli.mutants.repo.MutantRepository;
import com.meli.mutants.services.MutantService;
import com.meli.mutants.servicesImpl.MutantServiceImpl;

public class MutantsTest {

	@Autowired
	MutantService service;
	
	@Autowired
	MutantRepository repository = Mockito.mock(MutantRepository.class);
	
	@BeforeEach
	void setUp() {
		
		this.service = new MutantServiceImpl(repository);
		
	}
	
	@Test
	void isNotMutant() {
		
		
		
		String[] dna ={"AATTTAA",
						 "CAGTGCT",
						 "CAATGGC",
						 "AGCTTGC",
						 "ACTAGAT",
						 "CCTCTGT",
						 "CCTCTGT"};
		Boolean result = service.isMutant(dna);
		Assertions.assertEquals(Boolean.FALSE,result);
	}
	
	@Test
	void isMutant() {
		
		
		
		String[] dna ={"AATTTAA",
						 "CAGTGCT",
						 "CAATGGC",
						 "AGCTTGC",
						 "ACTAGCT",
						 "CCTCCGT",
						 "CCTCTGT"};
		Boolean result = service.isMutant(dna);
		Assertions.assertEquals(Boolean.TRUE,result);
	}
}
