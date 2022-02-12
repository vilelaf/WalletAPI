package com.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class WalletItemDTO {

	@Id
	private Long id;
	@NotNull (message = "Informe o id da carteira.")
	private Long wallet;
	@NotNull (message = "Informe uma data.")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date date;
	@NotNull (message = "Informe a destrição.")
	@Length (min = 5,  message = "Com no mínimo 5 caracteres.")
	private String description;
	@NotNull (message = "Informe a destrição.")
	@Pattern(regexp = "^(ENTRADA |SAÍDA)$", message = "Para o tipo somente são aceitos os valores de ENTRADA ou SAÍDA") 
	private String type;
	@NotNull (message = "Informe um valor, nem que seja 0.")
	private BigDecimal value;
	
}
