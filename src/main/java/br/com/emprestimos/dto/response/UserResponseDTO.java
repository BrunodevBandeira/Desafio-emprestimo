package br.com.emprestimos.dto.response;

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


    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.cpf = user.getCpf();
        this.name = user.getName();
        this.location = user.getLocation();
        this.age = user.getAge();
        this.income = user.getIncome();
    }


    public UserResponseDTO(Long id, String cpf2, String name2, String location2, int age2, int income2) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.location = location;
        this.age = age;
        this.income = income;
    }

}
