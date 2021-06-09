package com.wallet.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.response.Response;
import com.wallet.service.UserService;

// A anotação @Valid faz com que o body (o payload) seja validado.
// A anotação @RequestBody esse método força a ter um body não vazio.
// A BidindResult vai guardar as validações do DTO

@RestController
@RequestMapping("user") // A rota para o controle
public class UserControler {

	@Autowired
	private UserService service;
	
	@PostMapping //método que cria um usuário é um post
	public ResponseEntity <Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result) {

		Response <UserDTO> response = new Response<UserDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		User user = service.save(this.convertDtoToEntity(dto)); // Isso aqui não já seria igual a um novo usuário ? 
		response.setData(this.convertEntityToDto(user)); // 
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	private User convertDtoToEntity(UserDTO dto) {
		User u = new User();
		u.setId(dto.getId());
		u.setEmail(dto.getEmail());
		u.setName(dto.getName());
		u.setPassword(dto.getPassword());

		return u;
	}
	
	private UserDTO convertEntityToDto(User u) {
		UserDTO dto = new UserDTO();
		dto.setId(u.getId());
		dto.setEmail(u.getEmail());
		dto.setName(u.getName());
		dto.setPassword(u.getPassword());

		return dto;
	}
	
}
