package br.com.emprestimos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.emprestimos.dto.request.UserRequestDTO;
import br.com.emprestimos.dto.response.UserResponseDTO;
import br.com.emprestimos.service.UserService;
import br.com.emprestimos.service.exception.UserNotFound;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/loan")
public class UserController {
    
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findByID(@PathVariable Long id) {
        if(id < 0) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }

        UserResponseDTO userResponse = userService.findById(id);
        if(userResponse == null) {
            throw new EntityNotFoundException("Usuário não encontrada com o ID: " + id);
        }

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> user = userService.findAll();

        if(user.isEmpty()) {
            throw new EmptyResultDataAccessException("Nenhuma transação encontrada", 1);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> postEmprestimo(@RequestBody UserRequestDTO userRequestDTO, UriComponentsBuilder uriBuilder) {

        UserResponseDTO userResponseDTO = userService.postEmprestimo(userRequestDTO);
        URI uri = uriBuilder.path("/loan/{id}").buildAndExpand(userResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateEmprestimo(@PathVariable(name = "id") Long id, @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO user = userService.updateEmprestimo(id, userRequestDTO);
            return ResponseEntity.ok().body(user);
        } catch(UserNotFound e) {
            return ResponseEntity.notFound().build();
        } catch(Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().body("Transação excluída com sucesso");

        } catch (UserNotFound e) {
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
