package com.wallet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.wallet.util.enums.RoleEnum;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id // Pro JPA saber que esse vai ser o Id da nossa tabela 
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Pra gerar automaticamente o Id de forma sequencial
	private Long id;
	
	@Column(nullable = false) // Basicamente obriga a ter um valor, não permite valores nem vazios nem nulos
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	
	
	
	
}
