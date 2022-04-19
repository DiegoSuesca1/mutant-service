package com.meli.mutants.controllers.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.mutants.controllers.dto.StatsDTO;
import com.meli.mutants.controllers.repo.MutantRepository;
import com.meli.mutants.controllers.services.MutantService;
import com.meli.mutants.entity.TestAdnEntity;



@Service
public class MutantServiceImpl implements MutantService {
	
	public static final Character[] LETTERS_OK_STR = {'A','T','C','G'}; 
	public static final Short LETTER_CONSEC_MUTANT = 4;
	public static final Short SEQUENCE_CONSEC_MUTANT = 2;
	
	
	private Short letterConsec;
	private Short countSequence;
	private Character previus;
	
	@Autowired	
	MutantRepository repository;
	
	public MutantServiceImpl(MutantRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Boolean isMutant(String[] dna) {
		TestAdnEntity test = new TestAdnEntity();
		if(isMutantLogic(dna)) {
			test.setMutant(true);
			repository.save(test);
			return true;
		}else {
			test.setMutant(false);
			repository.save(test);
			return false;
		}
	}
	
	
	private Boolean isMutantLogic(String[] dna) {
		countSequence = 0;
		
		
		if(isMutantHorizontal(dna))
			return true;
		
		if(isMutantVertical(dna))
			return true;
								
		if(isMutantObliqSec1(dna))
			return true;
		
		if(isMutantObliqSec2(dna))
			return true;
		
		if(isMutantObliqSec3(dna))
			return true;
		
		if(isMutantObliqSec4(dna))
			return true;
		
		return false;
	}
	
	private Boolean isMutantHorizontal(String[] dna) {
		
		for (int i = 0; i < dna.length; i++) {
			newLine();
			 for (int j = 0; j < dna[i].length(); j++) {				 
					 char letter = dna[i].charAt(j);
					 
					 if(!isLetterOk(letter))
							return false; 
					 
					 if(isMutantRect(letter))
						 return true;						 	
		     }
		}
		return false;
	}
	
	private Boolean isMutantVertical(String[] dna) {
		
		for (int i = 0; i < dna[0].length(); i++) {
			newLine();
			 for (int j = 0; j < dna.length; j++) {
				 try {
					 char letter = dna[j].charAt(i);
					 
					 if(!isLetterOk(letter))
							return false; 
					 
					 if(isMutantRect(letter))
						 return true;
			 	}catch(IndexOutOfBoundsException e) {
					return false;
				}
		     }
		}
		return false;
	}
	
	/**
	 * 	Sector 1 array
	 * 	XXXX
		-XXX
		--XX
		---X
	 * @param dna array input
	 * @return
	 */
	private Boolean isMutantObliqSec1(String[] dna) {
		
		for (int i = 0; i <= dna[0].length()-LETTER_CONSEC_MUTANT; i++) {
			newLine();
			for (int j = 0; j < dna.length - i; j++) {
				try {
					char letter = dna[j].charAt(j+i);
					 if(isMutantRect(letter))
						 return true;
				}catch(IndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * 	Sector 2 array
	 * 	----
		X---
		XX--
		XXX-
	 * @param dna array input
	 * @return
	 */
	private Boolean isMutantObliqSec2(String[] dna) {
		
		for (int i = 1; i <= dna.length-LETTER_CONSEC_MUTANT; i++) {
			newLine();
			for (int j = 0; j < dna.length - i; j++) {
				try {			
					char letter = dna[j+i].charAt(j);
					if(isMutantRect(letter))
						 return true;
				}catch(IndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * 	Sector 3 array
	 * 	XXXX
		XXX-
		XX--
		X---
	 * @param dna array input
	 * @return
	 */
	private Boolean isMutantObliqSec3(String[] dna) {
		
		for (int i = dna[0].length()-1; i >= LETTER_CONSEC_MUTANT - 1; i--) {
			newLine();
			for (int j = 0; j <= i; j++) {
				try {
					char letter = dna[j].charAt(i-j);
					if(isMutantRect(letter))
						return true;
				}catch(IndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * 	Sector 4 array
	 * 	----
		---X
		--XX
		-XXX
	 * @param dna array input
	 * @return
	 */
	private Boolean isMutantObliqSec4(String[] dna) {
		
		for (int i = 1; i <= dna.length-LETTER_CONSEC_MUTANT; i++) {
			newLine();
			for (int j = 0; j < dna.length - i; j++) {
				try {
					char letter = dna[j+i].charAt(dna.length-1-j);
					if(isMutantRect(letter))
						 return true;
				}catch(IndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		return false;
	}
	
	private Boolean isMutantRect(Character letter) {
		
		
		if(letter.equals(previus)) {
			letterConsec++;
			
			if(letterConsec.equals(LETTER_CONSEC_MUTANT)) {
				System.out.println(previus);
				countSequence++;
			}
			if(letterConsec.compareTo(LETTER_CONSEC_MUTANT)>0)
				return true;
			
			if(countSequence.equals(SEQUENCE_CONSEC_MUTANT))
				return true;
			
		}else {
			letterConsec = 1;
		}
		
		previus = letter;
		return false;
	}
	
	private void newLine() {
		previus = '-';
		letterConsec = 1;
	}
	
	
	private Boolean isLetterOk(Character letter) {
		for (int i = 0; i < LETTERS_OK_STR.length; i++) {
			if(letter.equals(LETTERS_OK_STR[i])) {
				return true;
			}
		}
		return false;
	}


	@Override
	public StatsDTO getStats() {
		StatsDTO stats = new StatsDTO();
		List<Integer> statsInt = repository.getStats();
		
		stats.setCountMutantDna(statsInt.get(0));
		stats.setCountHumanDna(statsInt.get(1));
		
		if(stats.getCountHumanDna() > 0) 			
			stats.setRatio(Double.valueOf(stats.getCountMutantDna() / Double.valueOf(stats.getCountHumanDna())));
		
		return stats;
	}
 
}
