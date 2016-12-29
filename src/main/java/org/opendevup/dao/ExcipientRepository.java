package org.opendevup.dao;

import java.util.List;

import org.opendevup.entities.Excipient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExcipientRepository extends JpaRepository<Excipient, Long> {
	public List<Excipient> findByLibelle(String libelle);

	public Page<Excipient> findByLibelle(String nom, Pageable page);

	@Query("select e from Excipient e where e.libelle like:x")
	public Page<Excipient> chercherExcipient(@Param("x") String mc, Pageable page);

}
