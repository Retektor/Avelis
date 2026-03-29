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
	private String city;
	private String timezone;
	private String avatarUrl;
	private String coverUrl;
	private String stringRole;
}
