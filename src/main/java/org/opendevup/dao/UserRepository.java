package org.opendevup.dao;

import java.util.List;

import org.opendevup.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>  {

	public List<User> findByName(String name);

	public Page<User> findByName(String name, Pageable page);

	@Query("select e from User e where e.name like:x")
	public Page<User> chercherUsers(@Param("x") String mc, Pageable page);

}


