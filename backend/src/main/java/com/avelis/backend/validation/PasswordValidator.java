package com.avelis.backend.validation;

public class PasswordValidator {
	public boolean validate(String password, String confirmPassword) {
		final int MIN_LENGTH = 10;
		int errors = 0;
		
		if (password != confirmPassword) {
			errors++;
		}
		
		if (password.length() < MIN_LENGTH) {
			errors++;
		}
		
		//TODO: ДОБАВИТЬ БОЛЬШЕ ПРАВИЛ ВАЛИДАЦИИ
		
		return errors == 0;
	}
}
