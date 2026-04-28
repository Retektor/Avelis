package com.avelis.backend.dto;

import com.avelis.backend.domain.TutoringFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateTutorRequest {
	public Long userId;
	public String headline;
	public String about;
	public String educationText;
	public String experienceInYears;
	public String teachingMethology;
	public TutoringFormat tutoringFormat;
	public double hourlyRateRubles;
}