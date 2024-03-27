package br.com.emprestimos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeLoan type;

    private Double interestRate;

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

    public TypeLoan getType() {
        return TypeLoan.fromValue(type);
    }

    public void setType(TypeLoan type) {
        this.type = type.getValue();
    }
}