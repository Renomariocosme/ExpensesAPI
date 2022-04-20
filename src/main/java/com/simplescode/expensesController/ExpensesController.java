package com.simplescode.expensesController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.cglib.util.ParallelSorter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.simplescode.domain.Expense;
import com.simplescode.helper.NullAwareBeanUtilsBean;
import com.simplescode.repository.ExpenseRepository;

@RestController
public class ExpensesController {

	private ExpenseRepository expenseRepository;
	private NullAwareBeanUtilsBean beanUtilsBean;
	
	
	public ExpensesController(ExpenseRepository expenseRepository, NullAwareBeanUtilsBean beanUtilsBean) {
		this.expenseRepository = expenseRepository;
		this.beanUtilsBean = beanUtilsBean;
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
	public Expense save(@Valid @RequestBody Expense expense) {
		return expenseRepository.save(expense);
	}
	
	@PutMapping("/expenses/{id}")
	public Expense update(@Valid @RequestBody Expense expense,@PathVariable("id") Long id) {
		findById(id);
		expense.setId(id);		
		return expenseRepository.save(expense);
	}
	
	@PatchMapping("/expenses/{id}")
	public Expense patchUpdate(@RequestBody Expense expense,@PathVariable("id") Long id) throws IllegalAccessException, InvocationTargetException {
		Expense existingExpense = findById(id);
		
		
		beanUtilsBean.copyProperty(existingExpense, null, expense);
		System.out.println(existingExpense);
		return expenseRepository.save(expense);
	}
	
	@DeleteMapping("/expenses/{id}")
	public void delete(@PathVariable("id")Long id) {
		findById(id);
		
		expenseRepository.deleteById(id);
	}
	
	
}
