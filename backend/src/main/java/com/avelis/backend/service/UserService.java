package com.avelis.backend.service;

import java.util.Optional;

import com.avelis.backend.domain.User;
import com.avelis.backend.dto.CreateUserRequest;
import com.avelis.backend.dto.UpdateUserRequest;


public interface UserService {
	User createUser(CreateUserRequest req);

	Optional<User> findUserByEmail(String email);

	Optional<User> findUserByPhone(String phone);

	User updateUser(UpdateUserRequest req);

//	void deleteUserById(Long userId);
}
