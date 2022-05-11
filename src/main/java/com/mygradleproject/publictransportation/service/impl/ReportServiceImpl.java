package com.mygradleproject.publictransportation.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mygradleproject.publictransportation.entity.Log;
import com.mygradleproject.publictransportation.repository.LogRepository;
import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	
    @Autowired
    private LogRepository logRepository;
    

	@Override
	public BaseResponse getReport(Date date){
		
		BaseResponse response = new BaseResponse();
		
		List<Log> logList = new ArrayList<>();
		List<String> list = new ArrayList<>();
		
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateStart = format.parse(format.format(date));

			Date dt = new Date();
			dt.setTime(dateStart.getTime());
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();
			
			logList = logRepository.findByCreateDateBetween(dateStart, dt);
			
			if(logList != null) {
				logList.sort(Comparator.comparing(
					    fr -> fr.getCreateDate(),
					    Comparator.nullsLast(Comparator.naturalOrder())
					));
				logList.stream()
						.forEach(log -> list.add("log: " + log.getLog() + " related user: " + log.getUser().getName()
								+ " " + log.getUser().getSurname() + " date: "
								+ log.getCreateDate()));
			}
			response.setData(list);
			response.setStatus(HttpStatus.OK.value());
			response.setOperationMessage(BaseResponse.SUCCESS);
			if(logList == null) {
				response.setAdditionalInfo("log is not found");
			}else
				response.setAdditionalInfo(list.size() + " lines listed");
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setOperationMessage(BaseResponse.UNSUCCESS);
		}
		return response;
		
	}



}
