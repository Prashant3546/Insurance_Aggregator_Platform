package com.InsuranceApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.InsuranceApp.entity.Plan;

public interface InsuranceRepository extends JpaRepository<Plan, Integer> {

	

}
