package com.example.expense_tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Search method with non-null annotations
    List<Expense> findByDescriptionContainingIgnoreCase(String description);
}
