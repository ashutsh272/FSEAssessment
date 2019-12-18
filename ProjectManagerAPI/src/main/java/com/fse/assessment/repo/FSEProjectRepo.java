package com.fse.assessment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fse.assessment.entity.FSEProject;
import com.fse.assessment.entity.FSETask;

public interface FSEProjectRepo extends CrudRepository<FSEProject, Long> {

	List<FSEProject> findByProjectId(String projectId);

	List<FSEProject> findByProjectName(String projectName);
	
	List<FSEProject> findByStartDate(String startDate);
	
	List<FSEProject> findByEndDate(String endDate);
	
	List<FSEProject> findByPriority(String priority);

	@Query("SELECT t FROM FSETask t WHERE t.projectId = ?1")
	List<FSETask> getAllTasksByProjectId(String projectId);
	
	
	
	
	

}
