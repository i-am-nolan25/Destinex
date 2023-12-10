package com.team08.csci205_final_project.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

/**
 * Represents a Transaction entity in the system.
 */
@Document(collection = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    /** Unique identifier for the transaction */
    @Id
    private String transaction_id;

    /** Price of the product */
    private double product_price;

    /** Tax of the transaction */
    private double tax_price;

    /** Platform fee of the transaction */
    private double fee_price;

    /** Payment method of user */
    private String payment_method;

    /** ID of the customer */
    private String customer_id;

    /** ID of the provider */
    private String provider_id;

    /** ID of the job related to the transaction */
    private String job_id;

    /** Time when the transaction occurred */
    private LocalDate transaction_time;
}
