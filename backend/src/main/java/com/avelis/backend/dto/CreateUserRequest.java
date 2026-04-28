package com.avelis.backend.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserRequest {
	public String email;
	public String phone;
	public String lastName;
	public String firstName;
	public String middleName;
	public LocalDate birthday;
	public String password;
	public String confirmPassword;
	public String city;
	public String timezone;
	public String avatarUrl;
	public String coverUrl;
	public String stringRole;
}
