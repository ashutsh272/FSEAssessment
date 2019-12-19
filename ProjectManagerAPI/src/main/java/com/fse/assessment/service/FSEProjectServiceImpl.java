package com.fse.assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.assessment.common.ApplicationException;
import com.fse.assessment.entity.FSEProject;
import com.fse.assessment.entity.FSETask;
import com.fse.assessment.entity.FSEUser;
import com.fse.assessment.model.BaseResponse;
import com.fse.assessment.model.BaseResult;
import com.fse.assessment.model.FSEProjectDTO;
import com.fse.assessment.repo.FSEProjectRepo;
import com.fse.assessment.service.response.ViewAllProjectsResponse;

@Service
public class FSEProjectServiceImpl implements FSEProjectService {
	
	@Autowired 
	FSEProjectRepo fseProjectRepo;

	@Override
	public ViewAllProjectsResponse getAllProjects() {
		//Build Response
		ViewAllProjectsResponse response = new ViewAllProjectsResponse();
		
		try{
			List<FSEProject> dbProjectList = new ArrayList<FSEProject>();
			List<FSEProjectDTO> projectList = new ArrayList<FSEProjectDTO>();
			FSEProjectDTO projDTO = null;
			fseProjectRepo.findAll().forEach(projectDB->dbProjectList.add(projectDB));
			if(!dbProjectList.isEmpty()){
				response.setResult(new BaseResult());
				/*	dbProjectList.forEach(project->{
				 	projDTO = new FSEProjectDTO();
					projDTO.setProjectId(String.valueOf(project.getProjectId()));
					projDTO.setProjectName(project.getProjectName());
					projDTO.setPriority(Integer.valueOf(project.getPriority()));
					projDTO.setStartDate(project.getStartDate());
					projDTO.setEndDate(project.getEndDate());
					projDTO.setUserId(String.valueOf(project.getManager().getUserId()));
					projDTO.setStatus(project.getStatus());
					projDTO.setNoOfTasks(getTaskCount(project.getProjectId()));
					projDTO.setCompletedTaskCount(getCompletedTaskCount(project.getProjectId()));
					projectList.add(projDTO);
					
				});*///Need to check why not Working with forEach
				for(FSEProject project:dbProjectList){
					projDTO = new FSEProjectDTO();
					projDTO.setProjectId(String.valueOf(project.getProjectId()));
					projDTO.setProjectName(project.getProjectName());
					projDTO.setPriority(Integer.valueOf(project.getPriority()));
					projDTO.setStartDate(project.getStartDate());
					projDTO.setEndDate(project.getEndDate());
					projDTO.setUserId(String.valueOf(project.getManager().getUserId()));
					projDTO.setStatus(project.getStatus());
					projDTO.setNoOfTasks(getTaskCount(project.getProjectId()));
					projDTO.setCompletedTaskCount(getCompletedTaskCount(project.getProjectId()));
					projectList.add(projDTO);
				}
				response.setProjectList(projectList);
			}else{
				throw new ApplicationException();
			}
			
			
		}catch(ApplicationException ae){
			response.setResult(new BaseResult(ae));
		}catch(Exception e){
			response.setResult(new BaseResult(e));
		}finally{
			
		}
		
		return response;
	}
	
	//TODO
	private Integer getTaskCount(long projectId) {
		// TODO
		return 0;
	}

	//TODO
	private Integer getCompletedTaskCount(long ProjId) {
		String projectId = String.valueOf(ProjId);
		Integer taskCount = 0;
		List<FSETask> taskList = fseProjectRepo.getAllTasksByProjectId(ProjId);
		taskCount= taskList.size();
		return taskCount;
	}
	

	@Override
	public BaseResponse deleteProject(String projectId) {
		BaseResponse response = new BaseResponse();
		try{
			fseProjectRepo.delete(Long.valueOf(projectId));
			response.setResult(new BaseResult());
			
		}catch(Exception e){
			response.setResult(new BaseResult(e));
		}finally{
			
		}
		return response;
	}

	@Override
	public BaseResponse addProject(FSEProjectDTO projectDTO) {
		BaseResponse response = new BaseResponse();
		try{
			FSEProject project = new FSEProject();
			project.setProjectName(projectDTO.getProjectName());
			project.setStartDate(projectDTO.getStartDate());
			project.setEndDate(projectDTO.getEndDate());
			project.setPriority(projectDTO.getPriority());
			//TODO
			FSEUser fseUser = new FSEUser();
			fseUser.setUserId(Long.valueOf(projectDTO.getUserId()));
			project.setManager(fseUser);
			
			
			project.setStatus("In PROGRESS");
			fseProjectRepo.save(project);
			response.setResult(new BaseResult());
			
		}catch(Exception e){
			response.setResult(new BaseResult(e));
		}finally{
			
		}
		return response;
	}

	@Override
	public BaseResponse updateProject(FSEProject project) {
		// TODO Auto-generated method stub
		return null;
	}

}
