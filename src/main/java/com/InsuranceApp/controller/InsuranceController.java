package com.InsuranceApp.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.InsuranceApp.Service.InsuranceService;
import com.InsuranceApp.Utils.Response;
import com.InsuranceApp.entity.Plan;


@RestController
public class InsuranceController {

	@Autowired
	InsuranceService serv;

	/**************************************
	 * Add plan
	 ****************************************************************/

	@PostMapping(value = "/AddPlan")
	public ResponseEntity<Response> insertPlan(@Valid @RequestBody Plan plan) {
		Plan insurancePlan = serv.insertPlan(plan);
		if (insurancePlan != null) {
			return new ResponseEntity<Response>(new Response("Health Plan Registered Successfully", null),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Response>(new Response("Failed, Please try again", null), HttpStatus.BAD_REQUEST);
		}
	}

	/**************************************
	 * get all plan
	 ****************************************************************/

	@GetMapping(value = "/GetAllPlan")
	public ResponseEntity<Response> getAllPlan() {
		List<Plan> AllActivePlan = serv.getAllPlan();
		if (AllActivePlan.size() != 0) {
			return new ResponseEntity<Response>(new Response("All plan list", AllActivePlan), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("plan not found", null), HttpStatus.NOT_FOUND);
		}

	}

	/**************************************
	 * get plan By Id
	 ****************************************************************/

	@GetMapping(value = "/GetPlanById/{plan_id}")
	public Plan getPlanById(@PathVariable("plan_id") @Min(1) int plan_id) {

		return serv.getPlanById(plan_id);

	}

	/**************************************
	 * delete plan
	 ****************************************************************/

	@DeleteMapping(value = "/DeletePlan/{plan_id}")
	public ResponseEntity<Response> deletePlan(@PathVariable("plan_id") @Min(1) int plan_id) {
		try {
			Plan insurancePlan = serv.checkPlan(plan_id);
			if (insurancePlan != null) {

				return new ResponseEntity<Response>(new Response("Health plan deleted successfully", null),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(
						new Response("Health plan you are trying to delete does not exists", null),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response("Health plan you are trying to delete does not exists", null), HttpStatus.NOT_FOUND);
		}
	}

	/**************************************
	 * Update plan
	 ****************************************************************/

	@PutMapping(value = "/UpdatePlan")
	public ResponseEntity<Response> updatePlan(@Valid @RequestBody Plan plan)
	{
		try {
			   Plan insuancePlan = serv.checkPlan(plan.getPlan_id());
			   if (insuancePlan != null)
			   {
			        Plan newPlan = serv.updatePlan(insuancePlan);
				    if (newPlan.getPlan_id() != 0)
				    {
					return new ResponseEntity<Response>(new Response("insurance Plan Updated Successfully", null),
							HttpStatus.OK);
			        }
			   }
			   else {
						return new ResponseEntity<Response>(new Response("plan does not exists", null), HttpStatus.NOT_FOUND);
			   }
			   }
		 catch (Exception e) {
			return new ResponseEntity<Response>(new Response("plan does not exists", null), HttpStatus.NOT_FOUND);
	}
		return new ResponseEntity<Response>(new Response("plan does not exists", null), HttpStatus.NOT_FOUND);
	}
}
