package com.fse.assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.assessment.common.ApplicationException;
import com.fse.assessment.entity.FSEParentTask;
import com.fse.assessment.entity.FSEProject;
import com.fse.assessment.entity.FSETask;
import com.fse.assessment.entity.FSEUser;
import com.fse.assessment.model.BaseResponse;
import com.fse.assessment.model.BaseResult;
import com.fse.assessment.model.FSETaskDTO;
import com.fse.assessment.repo.FSEParentTaskRepo;
import com.fse.assessment.repo.FSETaskRepo;
import com.fse.assessment.service.response.ViewAllTasksResponse;

@Service
public class FSETaskServiceImpl implements FSETaskService {

	@Autowired
	FSETaskRepo fseTaskRepo;

	@Autowired
	FSEParentTaskRepo fseParentTaskRepo;

	@Override
	public ViewAllTasksResponse getAllTasks() {
		// Build Response
		ViewAllTasksResponse response = new ViewAllTasksResponse();

		try {
			List<FSETask> dbTaskList = new ArrayList<FSETask>();
			List<FSETaskDTO> taskList = new ArrayList<FSETaskDTO>();
			FSETaskDTO projDTO = null;
			fseTaskRepo.findAll().forEach(task -> dbTaskList.add(task));
			if (!dbTaskList.isEmpty()) {
				response.setResult(new BaseResult());
				for (FSETask task : dbTaskList) {
					projDTO = new FSETaskDTO();
					projDTO.setTaskId(String.valueOf(task.getTaskId()));
					projDTO.setTaskName(task.getTaskName());
					projDTO.setPriority(Integer.valueOf(task.getPriority()));
					projDTO.setStartDate(task.getStartDate());
					projDTO.setEndDate(task.getEndDate());
					taskList.add(projDTO);
				}
				response.setTaskList(taskList);
			} else {
				throw new ApplicationException();
			}

		} catch (ApplicationException ae) {
			response.setResult(new BaseResult(ae));
		} catch (Exception e) {
			response.setResult(new BaseResult(e));
		} finally {

		}

		return response;
	}

	@Override
	public ViewAllTasksResponse getAllParentTasks() {
		// Build Response
		ViewAllTasksResponse response = new ViewAllTasksResponse();

		try {
			List<FSEParentTask> dbParentTaskList = new ArrayList<FSEParentTask>();
			List<FSETaskDTO> taskList = new ArrayList<FSETaskDTO>();
			FSETaskDTO taskDTO = null;
			fseParentTaskRepo.findAll().forEach(parentTaskInDB -> dbParentTaskList.add(parentTaskInDB));
			if (!dbParentTaskList.isEmpty()) {
				response.setResult(new BaseResult());
				dbParentTaskList.forEach(task -> {
					

				});
				
				for(FSEParentTask task:dbParentTaskList){
					taskDTO = new FSETaskDTO();
					taskDTO.setTaskId(String.valueOf(task.getParentTaskId()));
					taskDTO.setTaskName(task.getParentTaskName());

					taskList.add(taskDTO);
				}
				
				response.setTaskList(taskList);
			} else {
				throw new ApplicationException();
			}

		} catch (ApplicationException ae) {
			response.setResult(new BaseResult(ae));
		} catch (Exception e) {
			response.setResult(new BaseResult(e));
		} finally {

		}

		return response;
	}

	@Override
	public BaseResponse addTask(FSETaskDTO taskDTO) {
		BaseResponse response = new BaseResponse();
		try {
			if (taskDTO.isParentTask()) {
				FSEParentTask parentTask = new FSEParentTask();
				parentTask.setParentTaskName(taskDTO.getTaskName());
				fseParentTaskRepo.save(parentTask);
			} else {
				FSETask task = new FSETask();
				task.setTaskName(taskDTO.getTaskName());
				task.setPriority(taskDTO.getPriority());
				task.setStartDate(taskDTO.getStartDate());
				task.setEndDate(taskDTO.getEndDate());

				if (taskDTO.getParentTaskId() != null) {
					FSEParentTask parentTask = new FSEParentTask();
					parentTask.setParentTaskId(Long.valueOf(taskDTO.getParentTaskId()));
					task.setParentTask(parentTask);

				}

				if (taskDTO.getUserId() != null) {
					FSEUser fseUser = new FSEUser();
					fseUser.setUserId(Long.valueOf(taskDTO.getUserId()));
					task.setTaskOwner(fseUser);
				}

				if (taskDTO.getProjectId() != null) {
					FSEProject project = new FSEProject();
					project.setProjectId(Long.valueOf(taskDTO.getProjectId()));
					task.setProject(project);
				}

				fseTaskRepo.save(task);
			}

			response.setResult(new BaseResult());

		} catch (Exception e) {
			response.setResult(new BaseResult(e));
		} finally {

		}
		return response;
	}

	@Override
	public BaseResponse updateTask(FSETaskDTO task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse deleteTask(String taskId) {
		BaseResponse response = new BaseResponse();
		try {
			fseTaskRepo.delete(Long.valueOf(taskId));
			response.setResult(new BaseResult());

		} catch (Exception e) {
			response.setResult(new BaseResult(e));
		} finally {

		}
		return response;
	}

}
