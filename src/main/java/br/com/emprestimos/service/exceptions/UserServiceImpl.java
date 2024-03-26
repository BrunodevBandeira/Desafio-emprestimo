package br.com.emprestimos.service.exceptions;

import br.com.emprestimos.dto.request.UserRequestDTO;
import br.com.emprestimos.dto.response.UserResponseDTO;

public class UserServiceImpl implements UserService{

    @Override
    public UserResponseDTO findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public UserResponseDTO findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public UserResponseDTO postEmprestimo(UserRequestDTO userRequestDTO) {
         
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
