package com.wallet.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

	private T data;  // O data é basicamente o tipo do payload que vai ser retornado.
	private List <String> errors; // Erro, falhas de validação vão pra cá.
	
	public List<String> getErrors(){
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}
	
}
