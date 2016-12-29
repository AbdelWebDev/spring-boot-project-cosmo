package org.opendevup.dao;

import java.util.List;

import org.opendevup.entities.Conservateur;
import org.opendevup.entities.Excipient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConservateurRepository extends JpaRepository<Conservateur, Long> {

	public List<Conservateur> findByLibelle(String libelle);

	public Page<Conservateur> findByLibelle(String nom, Pageable page);

	@Query("select e from Conservateur e where e.libelle like:x")
	public Page<Conservateur> chercherConservateur(@Param("x") String mc, Pageable page);

}
