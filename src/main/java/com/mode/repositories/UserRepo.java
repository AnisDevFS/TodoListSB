package com.mode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mode.entities.User;

@CrossOrigin("*")
@RepositoryRestResource
public interface UserRepo extends JpaRepository<User, Integer> {

}
