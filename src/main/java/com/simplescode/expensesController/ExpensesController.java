package com.simplescode.expensesController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.cglib.util.ParallelSorter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	
	@GetMapping("/expenses/{id}")
	public Expense findById(@PathVariable("id") Long id){
		 return expenseRepository.findById(id)
				 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/expenses")
	public Expense save(@RequestBody Expense expense) {
		return expenseRepository.save(expense);
	}
}
