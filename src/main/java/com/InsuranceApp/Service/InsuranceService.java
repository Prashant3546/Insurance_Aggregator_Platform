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
	InsuranceRepository repo;

	public Plan insertPlan(Plan plan) {

		return repo.saveAndFlush(plan);

	}

	public List<Plan> getAllPlan() {

		return repo.findAll();
	}

	public Plan getPlanById(Integer plan_Id) {

		return repo.getOne(plan_Id);
	}

	public void deletePlan(int plan_id) {

		repo.deleteById(plan_id);
	}

	public Plan updatePlan(Plan plan) {

		return repo.save(plan);

	}

	public void update(Plan plan, int plan_id) {

		repo.save(plan);

	}

	public Plan checkPlan(@Min(1) int plan_id) {
		
		return repo.getOne(plan_id);
	}

}
