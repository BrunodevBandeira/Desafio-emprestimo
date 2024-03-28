package br.com.emprestimos.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.emprestimos.dto.request.UserRequestDTO;
import br.com.emprestimos.dto.response.LoanResponseDTO;
import br.com.emprestimos.dto.response.UserResponseDTO;
import br.com.emprestimos.entity.Loan;
import br.com.emprestimos.entity.TypeLoan;
import br.com.emprestimos.entity.User;

@Component
public class UserMapper {
    public User toUser(UserRequestDTO userRequestDTO) {
        // toUser => converte um obj UserRequestDTO para um obj User
        return User.builder()
        .cpf(userRequestDTO.getCpf())
        .name(userRequestDTO.getName())
        .location(userRequestDTO.getLocation())
        .age(userRequestDTO.getAge())
        .income(userRequestDTO.getIncome()).build();
    }

    // public UserResponseDTO toUserDTO(User user) {
    //     // toUserDTO: Converte um objeto User em um objeto UserResponseDTO.
    //     return new UserResponseDTO(user.getId(), 
    //                                 user.getCpf(), 
    //                                 user.getName(), 
    //                                 user.getLocation(), 
    //                                 user.getAge(), 
    //                                 user.getIncome()
    //                                 );
    // }

    public UserResponseDTO toUserDTO(User user) {
        // toUserDTO: Converte um objeto User em um objeto UserResponseDTO.
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setCpf(user.getCpf());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setLocation(user.getLocation());
        userResponseDTO.setAge(user.getAge());
        userResponseDTO.setIncome(user.getIncome());

        List<LoanResponseDTO> loanResponseDTOs = user.getLoans().stream()
                .map(loan -> new LoanResponseDTO(loan.getType().toString(), loan.getInterestRate()))
                .collect(Collectors.toList());
        userResponseDTO.setLoans(loanResponseDTOs);

        return userResponseDTO;
    }


    public List<UserResponseDTO> toDTO(List<User> userList) {
        // toDTO: Converte uma lista de objetos userList em uma lista de UserResponseDTO.
        return userList.stream().map(UserResponseDTO::new).collect(Collectors.toList());

    }


    public void updateUser(User user, UserRequestDTO userRequestDTO) {
        // updateTransactionData: Atualiza os dados de um objeto Transaction com base nos dados fornecidos em um objeto TransactionRequestDTO.

        user.setCpf(userRequestDTO.getCpf());
        user.setName(userRequestDTO.getName());
        user.setLocation(userRequestDTO.getLocation());
        user.setAge(userRequestDTO.getAge());
        user.setIncome(userRequestDTO.getIncome());
    }

    public Loan toLoan(TypeLoan type, User user) {
        return new Loan(type, user);
    }
}
