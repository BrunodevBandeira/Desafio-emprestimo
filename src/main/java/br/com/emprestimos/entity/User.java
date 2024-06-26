package br.com.emprestimos.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name ="Tb_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY => fala pro banco de dados gerar a chave primário automaticamente confomre os registros forem inseridos na tabela
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "income", nullable = false)
    private int income;
    
    // mappedBy = "user": Este atributo indica o nome do atributo na classe Loan
    // cascade = CascadeType.ALL: Este atributo especifica que as operações de persistência realizadas no objeto User também devem ser aplicadas aos objetos Loan associados a ele.
    // Ou seja, se um User é deletado, todos os Loan associados a ele também serão deletados.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();

    public User() {}

    @Builder
    public User(Long id, String cpf, String name, String location, int age, int income) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.location = location;
        this.age = age;
        this.income = income;
    }
}
