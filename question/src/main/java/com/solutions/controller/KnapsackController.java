package com.solutions.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.solutions.bean.Data;
import com.solutions.services.KnapsakService;

@Controller
public class KnapsackController {

	@Autowired
	KnapsakService service;
	
	@RequestMapping(value="/knapsack-solution",method=RequestMethod.POST)
	@ResponseBody
	public int findSolution(@RequestParam("testCasefile") MultipartFile file){
		try {
			if(!file.isEmpty() || file!=null){
				Scanner sc = new Scanner(file.getInputStream());
				int max_weight = sc.nextInt();
				int totalItems = sc.nextInt();
				Data bean =  new Data(max_weight, totalItems);
				int[] weights = new int[totalItems];
				int[] value = new int[totalItems];
				int c=0;
				while(sc.hasNextInt()){
					value[c]=sc.nextInt();
					weights[c]=sc.nextInt();
					c++;
				}
				sc.close();
				bean.setWeights(weights);
				bean.setValues(value);
				return service.knapSack(bean);
			}
		} catch (Exception e) {
			
		}
		return -1;
	}
	
}
