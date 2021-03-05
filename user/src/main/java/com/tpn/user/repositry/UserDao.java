package com.tpn.user.repositry;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tpn.user.entity.User;

/**
 * 
 * @author Premnath T
 *
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

	Optional<User> findByUsername(String username);
}
