/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 11/5/2023
 * Time: 8:23 PM
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.service
 * Class: TransService
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.model.Transaction.Transaction;
import com.team08.csci205_final_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {


    @Autowired
    public TransactionRepository transactionRepository;

    /** Add a new user to the database */
    public Transaction addTransaction (Transaction transaction) {
        // Automatically set the register date
        transaction.setTransaction_time(LocalDate.now());

        return transactionRepository.save(transaction);
    }

    /** Find a transaction based on transaction_id */
    public Optional<Transaction> findTransactionById(String transactionId) {
        return transactionRepository.findById(transactionId);
    }

    /** Find a transaction based on job_id */
    public Optional<Transaction> findTransactionByJobId(String jobId) {
        return transactionRepository.findByJob_id(jobId);
    }

    /** Return all transactions in the database */
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }


    /** Delete a transaction based on their transactionId */
    public void deleteTransactionById(String transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId);
        }
        else {
            throw new RuntimeException("User not found with ID: " + transactionId);
        }
    }
}