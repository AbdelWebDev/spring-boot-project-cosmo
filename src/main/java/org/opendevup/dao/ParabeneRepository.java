package org.opendevup.dao;

import java.util.List;

import org.opendevup.entities.Parabene;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParabeneRepository extends JpaRepository<Parabene, Long> {

	public List<Parabene> findByLibelle(String libelle);

	public Page<Parabene> findByLibelle(String nom, Pageable page);

	@Query("select e from Parabene e where e.libelle like:x")
	public Page<Parabene> chercherParabene(@Param("x") String mc, Pageable page);

}
