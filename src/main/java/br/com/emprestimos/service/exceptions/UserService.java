package br.com.emprestimos.service.exceptions;

import br.com.emprestimos.dto.request.UserRequestDTO;
import br.com.emprestimos.dto.response.UserResponseDTO;

public interface UserService {
    
    UserResponseDTO findById(Long id);
    UserResponseDTO findAll();
    UserResponseDTO postEmprestimo(UserRequestDTO userRequestDTO);
    UserRequestDTO updateEmprestimo(UserRequestDTO userRequestDTO);
    String delete(Long id);
}
