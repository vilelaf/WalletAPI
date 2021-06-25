package com.wallet.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "users_wallet")
public class UserWallet implements Serializable {

	private static final long serialVersionUID = -8104860055294069590L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JoinColumn(name = "users", referencedColumnName = "id") // Ver pq o nome é users e no user controller ta user
	@ManyToOne(fetch = FetchType.LAZY) // Quando Carregar o usuário dentro da user_wallet ele vai trazer apenas o ID // caso queira acessar outras propriedades do user ele faz uma nova busca isso poupa processamento.
	private User users;
	@JoinColumn(name= "wallet", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Wallet wallet;
	
	
	
	
}
