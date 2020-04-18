package com.InsuranceApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int plan_id;

	@NotEmpty(message = "Please provide a plan name")
	@Size(min = 2, message = "Company name should be at least 2 character long.")
	String plan_Name;

	@NotEmpty(message = "Please provide company name")
	@Size(min = 4, message = "Company name should be at least 4 character long.")
	String company_Name;

	@NotNull(message = "Please provide a primium ammount")
	double premium_Ammount;

	int entry_age;

	int maturity_age;

	@Value("1")
	int status;

}
