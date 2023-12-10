package com.team08.csci205_final_project.repository;

import com.team08.csci205_final_project.model.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    /** Custom query method to find a user by email */
    @Query("{ 'email' : ?0 }")
    Optional<User> findByEmail(String email);
}
