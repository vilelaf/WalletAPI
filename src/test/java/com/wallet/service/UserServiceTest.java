package com.wallet.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@MockBean 
	UserRepository repository;
	
	@Autowired
	UserService service;
		
	@Before
	public void setUp() {
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new User()));
		//O método findByEmailEquals retorna um optional de user. No mockito tem a função mockito.anyString()
		//pra que independentemente do texto ele retorne tal coisa, no caso o optional de new user
	}
	
	@Test
	public void testFindByEmail() {
		Optional <User> user = service.findbyEmail("teste@email.com");
		assertTrue(user.isPresent());
	} 
	
	// Depois vem um método Save tmb, apesar de já ter sido testado na User Repository
	
}
