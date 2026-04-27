package com.avelis.backend.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tutors")
public class Tutor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tutor_id", nullable = false, updatable = false)
	private Long tutorId;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
	private Long userId;

	@Column(name="headline", nullable = false)
	private String headline;

	@Column(name = "about")
	private String about;
	
	@Column(name = "education_text", nullable = false)
	private String educationText;
	
	@Column(name = "experience_in_years")
	private String experienceInYears;
	
	@Column(name = "teaching_methology")
	private String teachingMethology;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TutoringFormat tutoringFormat;

	@Column(name = "hourly_rate_rubles", nullable = false)
	private double hourlyRateRubles;

	@Column(name = "is_published", nullable = false)
	private boolean isPublished;

	@Column(name = "is_verified", nullable = false)
	private boolean isVerified;

	@Column(name = "is_accepting_students", nullable = false)
	private boolean isAcceptingStudents;
	
	@Column(name = "created_at", updatable = false)
	@CreatedDate
	private Instant createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	private Instant updatedAt;
}
