package com.mygradleproject.publictransportation.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/getReportByDate")
	public BaseResponse getVehicleById(
			@RequestParam(value = "date", required = false) Date date){
		
		if(date == null)
			date = new Date();

		return reportService.getReport(date);
	}
	
}
