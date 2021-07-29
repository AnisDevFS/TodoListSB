package com.mode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mode.entities.User;
import com.mode.entities.Tache;
import com.mode.repositories.TacheRepo;
import com.mode.repositories.UserRepo;

@SpringBootApplication
public class TodoListSbApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TacheRepo tacheRepo;

	public static void main(String[] args) {
		SpringApplication.run(TodoListSbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			
	 	User anis = new User("ANis", "anis@pop.fr", "0000", 30);
        userRepo.save(anis);
	 	User seb = new User("Sébastien", "seb@pop.fr", "0000", 40);
        userRepo.save(seb);
        
        Tache t1 = new Tache("Acheter du lait");
        Tache t2 = new Tache("Acheter du pain");
        t1.setUser(seb); t2.setUser(seb);

        Tache t3 = new Tache("Acheter Chaussure de sport");
        Tache t4 = new Tache("les chaussures doivent etre des NIKE");
        t3.setUser(anis); t4.setUser(anis);
        
        tacheRepo.save(t1);        tacheRepo.save(t2);
        tacheRepo.save(t3);        tacheRepo.save(t4);
	}

}
