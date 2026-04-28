package com.avelis.backend.dto;

import com.avelis.backend.domain.TutoringFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateTutorRequest {
	public Long userId;
	public String headline;
	public String about;
	public String educationText;
	public String experienceInYears;
	public String teachingMethology;
	public TutoringFormat tutoringFormat;
	public double hourlyRateRubles;
	// Обновление параметров is... осуществляется со стороны администраторов
}