package com.avelis.backend.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.avelis.backend.domain.User;
import com.avelis.backend.domain.UserRole;
import com.avelis.backend.dto.CreateUserRequest;
import com.avelis.backend.repositories.UserRepository;
import com.avelis.backend.service.UserService;
import com.avelis.backend.validation.PasswordValidator;

public class UserServiceImpl implements UserService {
	private final UserRepository repo;
	private final PasswordValidator passwordValidator;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository repo, PasswordValidator passwordValidator, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordValidator = passwordValidator;
        this.passwordEncoder = passwordEncoder;
    }
	
	@Override
	@Transactional
	public User createUser(CreateUserRequest req) {
		passwordValidator.validate(req.getPassword(), req.getConfirmPassword());
		
		String hashedPassword = passwordEncoder.encode(req.password);
		
		UserRole role = parseUserRole(req.getStringRole());
		
		User user = User.builder()
				.email(req.getEmail())
				.phone(req.getPhone())
				.lastName(req.getLastName())
				.firstName(req.getLastName())
				.middleName(req.getMiddleName())
				.birthday(req.getBirthday())
				.passwordHash(hashedPassword)
				.city(req.getCity())
				.timezone(req.getTimezone())
				.avatarUrl(req.getAvatarUrl())
				.coverUrl(req.getCoverUrl())
				.role(role)
				.build();
		
		return repo.save(user);
	}

//	public Optional<User> findUserByEmail(String email);
	
//	public Optional<User> findUserByPhone(String phone);
	
//	public User updateUser(UpdateUserRequest req);
	
//	public void deleteUserById(Long userId);
	
	private UserRole parseUserRole(String stringRole) {
		String upperString = stringRole.toUpperCase();
		return switch (upperString) {
				case "STUDENT" -> UserRole.STUDENT;
				case "TUTOR" -> UserRole.TUTOR;
				case "PARENT" -> UserRole.PARENT;
				default -> throw new IllegalArgumentException("Неизвестная роль пользователя");
		};
	}
}
