package com.mygradleproject.publictransportation.service;

import java.util.Date;

import com.mygradleproject.publictransportation.response.BaseResponse;

public interface ReportService {
	
	public BaseResponse getReport(Date date);

}
