package com.avelis.backend.domain;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long userId;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(unique = true, nullable = false)
	private String phone;

	@Column(name = "password_hash", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private String passwordHash;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column
	private LocalDate birthday;

	@Column
	private String city;

	@Column
	private String timezone;

	@Column(name = "avatar_url")
	private String avatarUrl;

	@Column(name = "cover_url")
	private String coverUrl;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatus status;

	@Column(name = "is_email_verified", nullable = false)
	private boolean isEmailVerified;

	@Column(name = "is_phone_verified", nullable = false)
	private boolean isPhoneVerified;

	@Column(name = "last_login_at")
	private Instant lastLoginAt;

	@Column(name = "created_at", updatable = false)
	@CreatedDate
	private Instant createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	private Instant updatedAt;
}
