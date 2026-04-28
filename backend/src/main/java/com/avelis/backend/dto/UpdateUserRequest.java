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
	public Long userId;
//	Обновление электронной почты будет осуществляться через отдельную функцию
//	Обновление телефона будет осуществляться через отдельную функцию
//	Обновление пароля будет осуществляться через отдельную функцию
	public String lastName;
	public String firstName;
	public String middleName;
	public LocalDate birthday;
	public String city;
	public String timezone;
	public String avatarUrl;
	public String coverUrl;
}
