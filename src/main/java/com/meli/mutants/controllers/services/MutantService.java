package com.meli.mutants.controllers.services;

import org.springframework.stereotype.Service;

import com.meli.mutants.controllers.dto.StatsDTO;

@Service
public interface MutantService {
	
	Boolean isMutant(String[] dna);

	StatsDTO getStats();
}
