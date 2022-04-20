package com.simplescode.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

//classe referente ao dominio da aplicação
public class Expense {
	
	@Id
	private Long id;
	@NotEmpty
	private String description;
	@NotNull
	private LocalDate date;
	private Double value;
	@NotNull
	private Category category;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
		public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
		public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
