package com.avelis.backend.util;

import java.time.LocalDate;

import com.avelis.backend.dto.CreateUserRequest;

public class TestDataUtil {
	public static CreateUserRequest createUserRequest() {
		LocalDate birthday = LocalDate.now();
		return CreateUserRequest.builder()
				.email("JohnDoe@example.com")
				.phone("71234567890")
				.lastName("Doe")
				.firstName("John")
				.middleName("Julius")
				.birthday(birthday)
				.password("testPassword")
				.confirmPassword("testPassword")
				.stringRole("Student")
				.build();

	}
}
