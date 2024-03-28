package br.com.emprestimos.dto.response;

import java.util.List;

import br.com.emprestimos.entity.User;
import lombok.Data;

@Data
public class UserResponseDTO {
     // RESPONSE => respostas do banco
     private Long id;
     private String cpf;
     private String name;
     private String location;
     private int age;
     private int income;
     private List<LoanResponseDTO> loans;


    public UserResponseDTO() {}


    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.cpf = user.getCpf();
        this.name = user.getName();
        this.location = user.getLocation();
        this.age = user.getAge();
        this.income = user.getIncome();
    }


    public UserResponseDTO(Long id2, String cpf2, String name2, String location2, int age2, int income2) {
        this.id = id2;
        this.cpf = cpf2;
        this.name = name2;
        this.location = location2;
        this.age = age2;
        this.income = income2;
    }

}
