package com.InsuranceApp.Service;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.InsuranceApp.entity.Plan;
import com.InsuranceApp.repo.InsuranceRepository;

@Service

public class InsuranceService {

	@Autowired
	InsuranceRepository insuranceRepo;

	public Plan insertPlan(Plan plan) {
		return insuranceRepo.saveAndFlush(plan);

	}

	public List<Plan> getAllPlan() {
		return insuranceRepo.findAll();
	}

	public Plan getPlanById(Integer plan_Id) {
		return insuranceRepo.getOne(plan_Id);
	}

	public void deletePlan(int plan_id) {
		insuranceRepo.deleteById(plan_id);
	}

	public Plan updatePlan(Plan plan) {
		return insuranceRepo.saveAndFlush(plan);

	}

	public void update(Plan plan, int plan_id) {
		insuranceRepo.saveAndFlush(plan);

	}

	public Plan checkPlan(@Min(1) int plan_id) {
		return insuranceRepo.getOne(plan_id);
	}

}
