package com.avelis.backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.avelis.backend.domain.User;
import com.avelis.backend.domain.UserRole;
import com.avelis.backend.dto.CreateUserRequest;
import com.avelis.backend.repositories.UserRepository;
import com.avelis.backend.util.TestDataUtil;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
    private UserRepository repo;

    @Mock
    private com.avelis.backend.validation.PasswordValidator passwordValidator;

    @Mock
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
    	underTest = new UserServiceImpl(repo, passwordValidator, passwordEncoder);
    }

    @Test
    void createUser_savesAndReturnsUser() {
        CreateUserRequest testReq = TestDataUtil.createUserRequest();
        LocalDate birthday = LocalDate.now();
        
        User saved = User.builder()
        		.userId(1L)
				.email("JohnDoe@example.com")
				.phone("+71234567890")
				.lastName("Doe")
				.firstName("John")
				.middleName("Julius")
				.birthday(birthday)
				.passwordHash("hashed")
				.role(UserRole.STUDENT)
				.build();
        
        
        // Настройка моков
        when(passwordValidator.validate(testReq.getPassword(), testReq.getConfirmPassword())).thenReturn(true);
        when(passwordEncoder.encode(testReq.getPassword())).thenReturn("hashed");
        when(repo.save(any(User.class))).thenReturn(saved);

        User result = underTest.createUser(testReq);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(testReq.getEmail(), result.getEmail());

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(repo).save(captor.capture());
        User passed = captor.getValue();

        assertEquals(testReq.getEmail(), passed.getEmail());
        assertEquals(UserRole.STUDENT, passed.getRole());
        assertEquals(testReq.getPhone(), passed.getPhone());
        assertEquals("hashed", passed.getPasswordHash());
    }
    
    @Test
    void createUser_throwsExceptionWhenFieldsAreNull() {
    	CreateUserRequest nullReq = CreateUserRequest.builder()
				.email(null)
				.phone(null)
				.lastName(null)
				.firstName(null)
				.password(null)
				.confirmPassword(null)
				.stringRole(null)
				.build();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            underTest.createUser(nullReq);
        });

        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().toLowerCase().contains("почта") || ex.getMessage().toLowerCase().contains("поля"));
    }
    
    @Test
    void findUserByEmail_returnsUser() {
        CreateUserRequest testReq = TestDataUtil.createUserRequest();
        LocalDate birthday = LocalDate.now();
        
        User saved = User.builder()
        		.userId(1L)
				.email("JohnDoe@example.com")
				.phone("+71234567890")
				.lastName("Doe")
				.firstName("John")
				.middleName("Julius")
				.birthday(birthday)
				.passwordHash("hashed")
				.role(UserRole.STUDENT)
				.build();
        
        
        // Настройка моков
        when(repo.findByEmail("JohnDoe@example.com")).thenReturn(Optional.of(saved));

        Optional<User> result = underTest.findUserByEmail("JohnDoe@example.com");
        
        assertTrue(result.isPresent());
        assertEquals(saved.getUserId(), result.get().getUserId());
        assertEquals(saved.getEmail(), result.get().getEmail());	

        verify(repo).findByEmail("JohnDoe@example.com");
    }
    
    @Test
    void findUserByEmail_returnsEmptyWhenNotFound() {
    	when(repo.findByEmail(anyString())).thenReturn(Optional.empty());
    	
    	Optional<User> result = underTest.findUserByEmail("InvalidEmail@example.com");
    	
    	assertTrue(result.isEmpty());
    	verify(repo, times(1)).findByEmail("InvalidEmail@example.com");
    }
    
    @Test
    void findUserByEmail_throwsExceptionWhenOtherValues() {
    	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            underTest.findUserByEmail(null);
        });

        assertNotNull(ex.getMessage());
    }
}

