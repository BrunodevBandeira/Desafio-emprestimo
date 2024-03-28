package br.com.emprestimos.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequestDTO {
   //REQUEST => Dados que estão sendo enviados 
    private String cpf;
    private String name;
    private String location;
    private int age;
    private int income;
}
