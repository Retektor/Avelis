package com.avelis.backend.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
	private Long userId;
//	Обновление электронной почты будет осуществляться через отдельную функцию
//	Обновление телефона будет осуществляться через отдельную функцию
//	Обновление пароля будет осуществляться через отдельную функцию
	private String lastName;
	private String firstName;
	private String middleName;
	private LocalDate birthday;
	private String city;
	private String timezone;
	private String avatarUrl;
	private String coverUrl;
}
