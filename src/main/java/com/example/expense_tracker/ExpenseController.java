package com.example.expense_tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.time.YearMonth;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // New mapping for home page
    @GetMapping("/")
    public String showHomePage() {
        return "index";  // This will display the index.html
    }
    
    // List expenses with pagination
    @GetMapping("/expenses")
    public String listExpenses(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5); // Pagination (5 per page)
        Page<Expense> expensePage = expenseService.getAllExpenses(pageable);
        model.addAttribute("expenses", expensePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", expensePage.getTotalPages());
        return "expense-list";
    }

    // Show form to add a new expense
    @GetMapping("/expenses/new")
    public String showAddExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("isNew", true);
        return "expense-form";
    }

    // Show form to edit an existing expense
    @GetMapping("/expenses/edit/{id}")
    public String showEditExpenseForm(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        model.addAttribute("isNew", false);
        return "expense-form";
    }

    // Save a new or edited expense
    @PostMapping("/expenses")
    public String saveExpense(@ModelAttribute Expense expense) {
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }

    // Delete an expense
    @GetMapping("/expenses/delete/{id}")
    public String deleteExpense(@PathVariable("id") Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    // Search for expenses by description
    @GetMapping("/expenses/search")
    public String searchExpenses(@RequestParam("query") String query, Model model) {
        List<Expense> expenses = expenseService.searchExpenses(query);
        model.addAttribute("expenses", expenses);
        return "expense-list";
    }

    @GetMapping("/expenses/stats")
    public String showExpenseStats(Model model) {
        double totalExpenses = expenseService.getTotalExpenses();
        Map<YearMonth, Double> monthlyExpenses = expenseService.getMonthlyExpenses();
        Map<String, Double> categoryWiseExpenses = expenseService.getCategoryWiseExpenses();

        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("monthlyExpenses", monthlyExpenses);
        model.addAttribute("categoryWiseExpenses", categoryWiseExpenses);

        return "expense-stats";
    }
}
