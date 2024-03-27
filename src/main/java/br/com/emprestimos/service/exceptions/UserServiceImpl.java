package br.com.emprestimos.service.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emprestimos.dto.request.UserRequestDTO;
import br.com.emprestimos.dto.response.UserResponseDTO;
import br.com.emprestimos.entity.Loan;
import br.com.emprestimos.entity.TypeLoan;
import br.com.emprestimos.entity.User;
import br.com.emprestimos.repository.LoanRepository;
import br.com.emprestimos.repository.UserRepository;
import br.com.emprestimos.util.UserMapper;
import lombok.RequiredArgsConstructor;


/*
    1 - Conceder o empréstimo pessoal se o salário do cliente for igual ou inferior a R$ 3000.
    2 - Conceder o empréstimo pessoal se o salário do cliente estiver entre R$ 3000 e R$ 5000, se o cliente tiver menos de 30 anos e residir em São Paulo (SP).
    3 - Conceder o empréstimo consignado se o salário do cliente for igual ou superior a R$ 5000.
    4 - Conceder o empréstimo com garantia se o salário do cliente for igual ou inferior a R$ 3000.
    5 - Conceder o empréstimo com garantia se o salário do cliente estiver entre R$ 3000 e R$ 5000, se o cliente tiver menos de 30 anos e residir em São Paulo (SP).
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final LoanRepository loanRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserResponseDTO findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    @Override
    public UserResponseDTO postEmprestimo(UserRequestDTO userRequestDTO) {
        User user = userMapper.toUser(userRequestDTO);
    
        TypeLoan typeLoan;
        if (user.getIncome() <= 3000) {
            typeLoan = TypeLoan.PERSONAL;
        } else if (user.getIncome() > 3000 && user.getIncome() <= 5000 && user.getAge() < 30 && user.getLocation().equals("SP")) {
            typeLoan = TypeLoan.GUARANTEED;
        } else if (user.getIncome() >= 5000) {
            typeLoan = TypeLoan.CONSIGNMENT;
        } else {
            throw new RuntimeException("Unable to determine loan type");
        }
    
        Loan loan = userMapper.toLoan(typeLoan, user);
        user.getLoans().add(loan);
    
        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }
    

    @Override
    public UserRequestDTO updateEmprestimo(UserRequestDTO userRequestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmprestimo'");
    }

    @Override
    public String delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
