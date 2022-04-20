package com.meli.mutants.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.meli.mutants.entity.TestAdnEntity;

@Repository 
public interface MutantRepository extends JpaRepository<TestAdnEntity, Long>{
	
	@Query(value ="select COUNT(1) "
			+ "from TEST_ADN "
			+ "where mutant is true "
			+ "union all "
			+ "select COUNT(1) "
			+ "from TEST_ADN "
			+ "where mutant is false ", nativeQuery=true)
	public List<Integer> getStats();
	
}
