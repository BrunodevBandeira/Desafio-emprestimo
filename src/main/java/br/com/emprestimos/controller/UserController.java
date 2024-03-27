package br.com.emprestimos.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.emprestimos.dto.request.UserRequestDTO;
import br.com.emprestimos.dto.response.UserResponseDTO;
import br.com.emprestimos.service.exceptions.UserService;

@RestController
@RequestMapping("/loan")
public class UserController {
    
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerNewTransaction (@RequestBody UserRequestDTO userRequestDTO, UriComponentsBuilder uriBuilder) {
        UserResponseDTO userResponseDTO = userService.postEmprestimo(userRequestDTO);

        URI uri = uriBuilder.path("/loan/{id}").buildAndExpand(userResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponseDTO);

    }

    


}
