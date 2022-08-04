package com.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.entity.*;

@Repository
public interface UserRepository extends JpaRepository <User,Long> {

	Optional <User> findByEmailEquals(String email); 
	
}
