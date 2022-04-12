package com.simplescode.expensesController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.cglib.util.ParallelSorter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplescode.domain.Expense;
import com.simplescode.repository.ExpenseRepository;

@RestController
public class ExpensesController {

	private ExpenseRepository expenseRepository;
	
	public ExpensesController(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}
	
	
	@GetMapping("/expenses")
	public List<Expense> findAll(){
		return StreamSupport.stream(expenseRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
}
