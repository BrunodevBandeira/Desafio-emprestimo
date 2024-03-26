package br.com.emprestimos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emprestimos.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
