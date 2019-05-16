package com.example.GameGambler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.GameGambler.model.User;
/**
 * custom interface for fetching user details by username and password
 * @author Divya
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	@Query("SELECT u FROM User u where u.username=?1 and u.password=?2")
	List<User> findByUserNameAndPassword(String username,String password);
	

	@Query("SELECT u FROM User u where u.username=?1")
	List<User> isExistsUserName(String username);
}
