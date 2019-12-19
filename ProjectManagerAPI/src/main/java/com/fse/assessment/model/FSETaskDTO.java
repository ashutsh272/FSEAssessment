/**
 * 
 */
package com.fse.assessment.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author O3407
 *
 */

public class FSETaskDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String taskId;
	private String taskName;
	private Date startDate;
	private Date endDate;
	private Integer priority;
	private String status;
	private Long userId;
	private Long parentTaskId;
	private boolean parentTask;
	private Long projectId;
	
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(Long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public boolean isParentTask() {
		return parentTask;
	}
	public void setParentTask(boolean parentTask) {
		this.parentTask = parentTask;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	
	
	
}
