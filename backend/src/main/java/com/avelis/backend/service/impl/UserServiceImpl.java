package com.avelis.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		if (req == null) {
			throw new IllegalArgumentException("Запрос пуст");
		}
		List<String> missing = new ArrayList<>();
	    if (isBlank(req.getEmail())) missing.add("электронная почта");
	    if (isBlank(req.getPhone())) missing.add("телефон");
	    if (isBlank(req.getFirstName())) missing.add("имя");
	    if (isBlank(req.getLastName())) missing.add("фамилия");
	    if (isBlank(req.getPassword())) missing.add("пароль");
	    if (isBlank(req.getConfirmPassword())) missing.add("подтверждение пароля");
	    
	    if (!missing.isEmpty()) {
	        throw new IllegalArgumentException("Не заполнены обязательные поля: " + String.join(", ", missing));
	    }
		
		passwordValidator.validate(req.getPassword(), req.getConfirmPassword());
		
		String hashedPassword = passwordEncoder.encode(req.password);
		
		UserRole role = parseUserRole(req.getStringRole());
		
		User user = User.builder()
				.email(req.getEmail().strip())
				.phone(req.getPhone().strip())
				.lastName(req.getLastName().strip())
				.firstName(req.getLastName().strip())
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

	public Optional<User> findUserByEmail(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Почта не введена");
		}
		return repo.findByEmail(email);
	}
	
	public Optional<User> findUserByPhone(String phone) {
		if (phone == null) {
			throw new IllegalArgumentException("Почта не введена");
		}
//		Удаление +, если он там есть
		if (phone.charAt(0) == '+') {
			phone = phone.substring(1);
		}
		
		return repo.findByPhone(phone);
	}
	
//	public User updateUser(UpdateUserRequest req);
	
//	public void deleteUserById(Long userId);
	
	private UserRole parseUserRole(String stringRole) {
		if (stringRole == null) {
			throw new IllegalArgumentException("Роль пользователя пуста");
		}
		String upperString = stringRole.toUpperCase();
		return switch (upperString) {
				case "STUDENT" -> UserRole.STUDENT;
				case "TUTOR" -> UserRole.TUTOR;
				case "PARENT" -> UserRole.PARENT;
				default -> throw new IllegalArgumentException("Неизвестная роль пользователя");
		};
	}
	
	private boolean isBlank(String s) {
	    return s == null || s.strip().isEmpty();
	}
}
