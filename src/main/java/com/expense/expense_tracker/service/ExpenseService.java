package com.expense.expense_tracker.service;

import com.expense.expense_tracker.model.Expense;
import com.expense.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) throws Exception {
        return expenseRepository.findById(id).map(expense -> {
            expense.setDescription(updatedExpense.getDescription());
            expense.setAmount(updatedExpense.getAmount());
            expense.setDate(updatedExpense.getDate());
            expense.setCategory(updatedExpense.getCategory());
            return expenseRepository.save(expense);
        }).orElseThrow(() -> new Exception("Expense not found with id " + id));
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate);
    }
}
