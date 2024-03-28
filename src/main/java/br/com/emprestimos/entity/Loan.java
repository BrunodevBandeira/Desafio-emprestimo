package br.com.emprestimos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name ="Tb_loan")
@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeLoan type;

    private Double interestRate;

    //JoinColumn => especifica a coluna na tabela do banco de dados que será usada para armazenar a chave estrangeira que faz a ligação entre as entidades Loan e User
    // No caso, a coluna user_id será criada na tabela Tb_loan para armazenar o identificador do usuário associado a cada empréstimo.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Loan() {}

    public Loan(TypeLoan type, User user) {
        this.type = type;
        this.user = user;

        switch (type) {
            case PERSONAL:
                this.interestRate = 4.00;
                break;
            case GUARANTEED:
                this.interestRate = 3.00;
                break;
            case CONSIGNMENT:
                this.interestRate = 2.00;
                break;
            default:
                break;
        }
        
    }  
}