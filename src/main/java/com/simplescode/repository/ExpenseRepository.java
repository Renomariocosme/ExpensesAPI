package com.simplescode.repository;

import org.springframework.data.repository.CrudRepository;
import com.simplescode.domain.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

	
}
