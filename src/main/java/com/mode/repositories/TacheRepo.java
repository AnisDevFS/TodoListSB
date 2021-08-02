package com.mode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mode.entities.Tache;

@CrossOrigin("*")
@RepositoryRestResource
public interface TacheRepo extends JpaRepository<Tache, Integer>{
	
	@Query("select t from Tache t where lower(t.texte) like :x and t.user.id_user like :y")
	public List<Tache> chercher(@Param("x")String mc ,
			@Param("y")Integer id_user);

}
