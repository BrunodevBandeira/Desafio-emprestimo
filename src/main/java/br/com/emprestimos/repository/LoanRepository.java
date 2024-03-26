package br.com.emprestimos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emprestimos.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
    
}
