package com.example.expense_tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.YearMonth;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Method to retrieve paginated list of expenses
    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    // Search method to filter expenses by description
    public List<Expense> searchExpenses(String query) {
        return expenseRepository.findByDescriptionContainingIgnoreCase(query);
    }

    // Other CRUD methods (optional):
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    // Method to calculate total expenses
    public double getTotalExpenses() {
        return expenseRepository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Method to calculate monthly expenses
    public Map<YearMonth, Double> getMonthlyExpenses() {
        return expenseRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                    expense -> YearMonth.from(expense.getDate()),
                    Collectors.summingDouble(Expense::getAmount)
                ));
    }

    // Method to calculate category-wise expenses
    public Map<String, Double> getCategoryWiseExpenses() {
        return expenseRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                    Expense::getCategory,
                    Collectors.summingDouble(Expense::getAmount)
                ));
    }
}
