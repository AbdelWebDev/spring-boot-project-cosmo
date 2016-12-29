package org.opendevup.dao;

import java.util.List;

import org.opendevup.entities.Principe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrincipeRepository extends JpaRepository<Principe, Long> {
	public List<Principe> findByLibelle(String libelle);

	public Page<Principe> findByLibelle(String nom, Pageable page);

	@Query("select e from Principe e where e.libelle like:x")
	public Page<Principe> chercherPrincipe(@Param("x") String mc, Pageable page);

}
