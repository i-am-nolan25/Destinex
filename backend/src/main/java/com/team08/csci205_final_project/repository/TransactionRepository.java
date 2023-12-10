package com.team08.csci205_final_project.repository;

import com.team08.csci205_final_project.model.Transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    /** Custom query to find a transaction by Job ID */
    @Query("{ 'job_id' : ?0 }")
    Optional<Transaction> findByJob_id(String job_id);
}
