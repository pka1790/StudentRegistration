package com.reg.beans;

public class SectionBean {
	
	private int sno,courseid,capacity,act,rem;
	private String title,sem,year,instructor,location;
	
	
	public SectionBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public SectionBean(int sno, int courseid, int capacity, int act, int rem, String title, String sem, String year,
			String instructor, String location) {
		this.sno = sno;
		this.courseid = courseid;
		this.capacity = capacity;
		this.act = act;
		this.rem = rem;
		this.title = title;
		this.sem = sem;
		this.year = year;
		this.instructor = instructor;
		this.location = location;
	}




	public void setSno(int sno) {
		this.sno = sno;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setAct(int act) {
		this.act = act;
	}
	public void setRem(int rem) {
		this.rem = rem;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSem(String sem) {
		this.sem = sem;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSno() {
		return sno;
	}
	public int getCourseid() {
		return courseid;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getAct() {
		return act;
	}
	public int getRem() {
		return rem;
	}
	public String getTitle() {
		return title;
	}
	public String getSem() {
		return sem;
	}
	public String getYear() {
		return year;
	}
	public String getInstructor() {
		return instructor;
	}
	public String getLocation() {
		return location;
	}


	@Override
	public String toString() {
		return "SectionBean [sno=" + sno + ", courseid=" + courseid + ", capacity=" + capacity + ", act=" + act
				+ ", rem=" + rem + ", title=" + title + ", sem=" + sem + ", year=" + year + ", instructor=" + instructor
				+ ", location=" + location + "]";
	}
	
	
	
	
	
	
	
	

}
