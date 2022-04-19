package com.meli.mutants.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutants.controllers.dto.DnaDTO;
import com.meli.mutants.controllers.dto.StatsDTO;
import com.meli.mutants.controllers.services.MutantService;

@RestController 
@RequestMapping() 
public class MutantController {

	@Autowired  
	MutantService service;	 
	
	@PostMapping("/mutant/")
	public ResponseEntity<Boolean> isMutant(@RequestBody DnaDTO dto) {
		Boolean isMutant = service.isMutant(dto.getDna());
		return new ResponseEntity<>(isMutant, 
				new HttpHeaders(), 
				isMutant?HttpStatus.OK:HttpStatus.FORBIDDEN);		
	}
	
	@GetMapping("/stats")
	public ResponseEntity<StatsDTO> stats() {		
		return new ResponseEntity<>(service.getStats(),HttpStatus.OK);		
	}
	
}