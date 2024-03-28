package br.com.emprestimos.service;

import java.util.List;

import br.com.emprestimos.dto.request.UserRequestDTO;
import br.com.emprestimos.dto.response.UserResponseDTO;

public interface UserService {
    
    UserResponseDTO findById(Long id);
    List<UserResponseDTO> findAll();
    UserResponseDTO postEmprestimo(UserRequestDTO userRequestDTO);
    UserResponseDTO updateEmprestimo(Long id, UserRequestDTO userRequestDTO);
    String delete(Long id);
}
