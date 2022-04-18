package com.meli.mutants.controllers.services;

import org.springframework.stereotype.Service;

@Service
public interface MutantService {
	
	Boolean isMutant(String[] dna);

}
