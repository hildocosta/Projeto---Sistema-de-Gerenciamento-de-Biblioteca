package com.hildo.costa.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "loans") // Define a coleção no MongoDB
public class Loan {

    @Id
    private String id;          // Identificador único
    private String bookId;      // ID do livro emprestado
    private String borrower;    // Nome do usuário que pegou o livro
    private LocalDate loanDate; // Data do empréstimo
    private LocalDate dueDate;  // Data de devolução prevista

    // Construtor padrão
    public Loan() {
    }

    // Construtor completo
    public Loan(String id, LocalDate dueDate, LocalDate loanDate, String borrower, String bookId) {
        this.id = id;
        this.dueDate = dueDate;
        this.loanDate = loanDate;
        this.borrower = borrower;
        this.bookId = bookId;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
