package com.InsuranceApp.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.InsuranceApp.Service.InsuranceService;
import com.InsuranceApp.Utils.Response;
import com.InsuranceApp.entity.Plan;
import com.InsuranceApp.exception.*;

@RestController
//@CrossOrigin(origins = { "http://localhost:9000" }, maxAge = 3000)
@RequestMapping("HealthInsurancePlan")
public class InsuranceController {

	@Autowired
	InsuranceService HealthInsuranceService;

	@PostMapping(value = "/AddPlan")
	public ResponseEntity<Response> insertPlan(@Valid @RequestBody Plan plan) {
		Plan insurancePlan = HealthInsuranceService.insertPlan(plan);
		if (insurancePlan != null) {
			return new ResponseEntity<Response>(new Response("Health Plan Registered Successfully", null),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Response>(new Response("Failed, Please try again", null), HttpStatus.BAD_REQUEST);
		}
	}


    @CrossOrigin(origins = { "http://localhost:8484" }, maxAge = 6000)
	@GetMapping(value = "/GetAllPlan")
	public ResponseEntity<Response> getAllPlan() throws ObjectNotFoundException {
		List<Plan> AllActivePlan = HealthInsuranceService.getAllPlan();
		if (AllActivePlan.size() != 0) { 
			return new ResponseEntity<Response>(new Response("All plan list", AllActivePlan), HttpStatus.OK);
		} else {
			throw new ObjectNotFoundException("Health Insurance Plans does not exists.");
		}
	}

	@GetMapping(value = "/GetPlanById/{plan_id}")
	public ResponseEntity<Response> getPlanById(@PathVariable("plan_id") @Min(1) int plan_id)
			throws ObjectNotFoundException {

		Plan result = HealthInsuranceService.getPlanById(plan_id);
		if (result != null) {
			return new ResponseEntity<Response>(new Response("Success", result), HttpStatus.OK);
		} else {
			throw new ObjectNotFoundException("Health Insurance Plan for provided id does not exists.");
		}
	}

	@DeleteMapping(value = "/DeletePlan/{plan_id}")
	public ResponseEntity<Response> deletePlan(@PathVariable("plan_id") @Min(1) int plan_id)
			throws ObjectNotFoundException {

		Plan insurancePlan = HealthInsuranceService.checkPlan(plan_id);
		if (insurancePlan != null) {

			return new ResponseEntity<Response>(new Response("Health plan deleted successfully", null), HttpStatus.OK);
		} else {
			throw new ObjectNotFoundException("Health Insurance Plan with provided id does not exists.");
		}

	}

	@PutMapping(value = "/UpdatePlan/{plan_id}")
	public ResponseEntity<Response> updatePlan(@Valid @PathVariable("plan_id") long plan_id, @RequestBody Plan plan)
			throws ObjectNotFoundException {

		Plan insuancePlan = HealthInsuranceService.checkPlan(plan.getPlan_id());
		if (insuancePlan != null) {
			HealthInsuranceService.updatePlan(insuancePlan);
			return new ResponseEntity<Response>(new Response("insurance Plan Updated Successfully", null),
					HttpStatus.OK);
		} else {
			throw new ObjectNotFoundException("Health Insurance Plan for given Id does not exists.");
		}
	}
}