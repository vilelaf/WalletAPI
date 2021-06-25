package com.wallet.dto;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class WalletDTO {
	
	@Id
	private Long id;
	@Length (min = 3, message = "O nome não pode ter menos de 3 caracteres.")
	@NotNull(message = "O nome não pode ser nulo.")
	private String name;
	@NotNull(message = "O valor também não pode ser nulo.")
	private BigDecimal value;

}
