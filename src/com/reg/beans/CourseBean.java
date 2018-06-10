package com.reg.beans;

public class CourseBean {

	private int courseid;
	private String cname,desc,department;



	public CourseBean() {
		// TODO Auto-generated constructor stub
	}


	public CourseBean(int courseid, String cname, String desc, String department) {
		super();
		this.courseid = courseid;
		this.cname = cname;
		this.desc = desc;
		this.department = department;
	}



	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getCourseid() {
		return courseid;
	}
	public String getCname() {
		return cname;
	}
	public String getDesc() {
		return desc;
	}
	public String getDepartment() {
		return department;
	}


	@Override
	public String toString() {
		return "CourseBean [courseid=" + courseid + ", cname=" + cname + ", desc=" + desc + ", department=" + department
				+ "]";
	}




}
