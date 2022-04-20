package com.meli.mutants.services;

import org.springframework.stereotype.Service;

import com.meli.mutants.dto.StatsDTO;

@Service
public interface MutantService {
	
	Boolean isMutant(String[] dna);

	StatsDTO getStats();
}
