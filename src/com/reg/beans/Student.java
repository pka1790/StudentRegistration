package com.reg.beans;

public class Student {
	//S_SSN, Name, DOB, Email, Address, Major
	
	private String sname,dob,emailid,address,major,password;
	private int stssn;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
		
	public Student(String sname, String dob, String emailid, String address, String major, int stssn,String password) {
		this.sname = sname;
		this.dob = dob;
		this.emailid = emailid;
		this.address = address;
		this.major = major;
		this.stssn = stssn;
		this.password=password;
	}

 
	public void setPassword(String password) {
		this.password = password;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setStssn(int stssn) {
		this.stssn = stssn;
	}
	public String getSname() {
		return sname;
	}
	public String getDob() {
		return dob;
	}
	public String getEmailid() {
		return emailid;
	}
	public String getAddress() {
		return address;
	}
	public String getMajor() {
		return major;
	}
	public int getStssn() {
		return stssn;
	}
	


	public String getPassword() {
		return password;
	}


	@Override
	public String toString() {
		return "Student [sname=" + sname + ", dob=" + dob + ", emailid=" + emailid + ", address=" + address + ", major="
				+ major + ", password=" + password + ", stssn=" + stssn + "]";
	}



	
	
	
	
}
