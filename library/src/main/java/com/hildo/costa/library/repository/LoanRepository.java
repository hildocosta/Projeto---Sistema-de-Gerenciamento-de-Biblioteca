package com.hildo.costa.library.repository;

import com.hildo.costa.library.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepository extends MongoRepository<Loan, String> {
}
