package com.mygradleproject.publictransportation.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mygradleproject.publictransportation.entity.Log;

@Repository
@Transactional
public interface LogRepository extends CrudRepository<Log, Integer> {

	List<Log> findByCreateDateBetween(Date from, Date to);

	@Modifying
    @Query(value = "insert into log(log, create_user_id) VALUES (:log, :createUserId)", nativeQuery = true)
    @Transactional
    void insertLog(@Param("log") String log, @Param("createUserId") int createUserId);
	
//	@Modifying
//	@Query(value = " l.log, u.name, u.surname from public.log l inner join public.user u on l.create_user_id = u.id where create_date between :startDate and :endDate ", nativeQuery = true)
//	@Transactional
//	List<Report> findByCreateDateBetween(@Param("startDate")Date startDate,@Param("endDate")Date endDate);


	
}
