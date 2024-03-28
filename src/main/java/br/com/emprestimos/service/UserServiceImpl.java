package br.com.emprestimos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emprestimos.dto.request.UserRequestDTO;
import br.com.emprestimos.dto.response.UserResponseDTO;
import br.com.emprestimos.entity.Loan;
import br.com.emprestimos.entity.TypeLoan;
import br.com.emprestimos.entity.User;
// import br.com.emprestimos.repository.LoanRepository;
import br.com.emprestimos.repository.UserRepository;
import br.com.emprestimos.util.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    // @Autowired
    // private final LoanRepository loanRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO findById(Long id) {
        return userMapper.toUserDTO(returnUser(id));
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userMapper.toDTO(userRepository.findAll());
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
    

        return userMapper.toUserDTO(userRepository.save(user));
    }
    

    @Override
    public UserResponseDTO updateEmprestimo(Long id, UserRequestDTO userRequestDTO) {
        User userId = returnUser(id);
        userMapper.updateUser(userId, userRequestDTO);
        return userMapper.toUserDTO(userRepository.save(userId));
    }

    @Override
    public String delete(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    private User returnUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("==> ID não encontrado <=="));

    }
 
    
}
