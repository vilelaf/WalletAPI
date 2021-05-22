package com.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;

// Isso aqui é uma classe de teste de repositório

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	public static final String EMAIL = "Teste@teste.com";
	
	@Autowired
	UserRepository repository;
	
	@Before //Executado Antes do teste 
	public void setUp() {
		User u = new User();
		u.setName("Set up User");
		u.setPassword("1234");
		u.setEmail(EMAIL);
		
		repository.save(u);
	}
	
	@After // executado depois do teste
	public void tearDown() {
		repository.deleteAll();
	}
	
	
	@Test
	public void testSave() {
 		User u = new User();
		u.setName("Teste");
		u.setPassword("123456");
		u.setEmail("teste@teste.com");
		
		User response = repository.save(u);
		
		assertNotNull(response); // garantir que não veio nula.
	}
	
	public void testFindByEmail() {
		Optional <User> response = repository.findByEmailEquals(EMAIL);  
		
		assertTrue(response.isPresent()); // Pra validar que o usuário existe
		assertEquals(response.get().getEmail(), EMAIL); // Pra ver se ele é o mesmo email que tem lá em cima
	}

}


