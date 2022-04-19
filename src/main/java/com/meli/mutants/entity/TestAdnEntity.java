package com.meli.mutants.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TEST_ADN")
public class TestAdnEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Long id;

	
    @Column(name="mutant")    
    private Boolean mutant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getMutant() {
		return mutant;
	}

	public void setMutant(Boolean mutant) {
		this.mutant = mutant;
	}
    
    
     
}
