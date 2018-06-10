package com.reg.service;

import java.util.List;

import com.reg.beans.CourseBean;
import com.reg.beans.SectionBean;
import com.reg.beans.Student;
import com.reg.dao.CourseDaoImpl;


public class CRService {
	private static final String DB_DRIVER="com.mysql.jdbc.Driver";
	private static final String CONN_URL="jdbc:mysql://localhost:3306/creg_schema";
	private static final String USERNAME="root";
	private static final String PASS="root";	
	private static final String REG_QUERY="INSERT INTO STUDENT"+ "(STSSN,SNAME,PASSWORD,DOB,EMAILID,MAJOR,ADDRESS) VALUES"+ "(?,?,?,?,?,?,?)";
	private static final String GET_CREDS="SELECT stssn,sname,password,dob,emailid,major,address from student where sname = "+"?";
	//	private static final String INS_TRIP="INSERT INTO TRIPS"+ "(STARTPOINT,ENDPOINT,DISTANCE,GENERATORID,TRANSPORTDTTM,SPECIALINSTRUCTION,VEHICLEPREFERENCE) VALUES"+ "(?,?,?,?,?,?,?)";
	
	//select course_id, c_name, description from course where department='CS'
	private static final String GET_CORS_LIST="select course_id, c_name, description from course where department ="+"?";
	private static final String SECTIONS_DATA="select s.s_no,s.course_id,c.c_name,s.semester,s.year,s.capacity,s.actual,s.rem,"+"t.tname,s.location from section s,"+"faculty t,"+"course c where c.course_id=s.course_id and "+
	"s.tssn=t.t_ssn and "+"c.course_id="+"?";
	
	private static final String UPDATE_SECS="UPDATE section SET actual ="+" ?"+" , rem="+"?"+" WHERE s_no ="+"?";
	private static final String INS_REGS="INSERT INTO csregister"+ "(stssn,s_no,course_id) VALUES"+ "(?,?,?)";
	//	private static final String UPDATE_TRIP="update trips set tripStatus = 'ACEEPTED'"+", carrierId= "+"?"+" where tripid ="+"?";
	//	private static final String ACCEPTED_TRIPS="select t.tripid,t.tripStatus,t.startpoint,t.endpoint,t.distance,t.vehiclePreference,u.username from trips t inner join users u on u.userid=t.generatorId "
	//			+ "where t.carrierid="+"?";
	public boolean registerUser(Student student) {
		CourseDaoImpl daoIimpl= new CourseDaoImpl();
		if(student!=null){

			return daoIimpl.insertStudent(DB_DRIVER,CONN_URL,USERNAME,PASS,REG_QUERY,student);




		}
		return false;
	}
	public Student logIn(String username2) {
		// TODO Auto-generated method stub
		CourseDaoImpl daoIimpl= new CourseDaoImpl();
		if(username2!=null){
			Student student1=daoIimpl.getCreds(DB_DRIVER, CONN_URL, USERNAME, PASS, GET_CREDS,username2);
			return student1;
		}
		return null;
	}
	/**
	 * Method to get course list
	 * @param option
	 * @return
	 */
	public List<CourseBean> getCourseDetails(String option) {
		CourseDaoImpl daoImpl= new CourseDaoImpl();

		return daoImpl.getCrsList(DB_DRIVER,CONN_URL,USERNAME,PASS,GET_CORS_LIST,option);
	}
	/**
	 * get section list
	 * @param crsId
	 * @return
	 */
	public List<SectionBean> getsectionList(int crsId) {
//		"select s.s_no,s.course_id,c.c_name,s.semester,s.year,s.capacity,s.actual,s.rem,"+"t.tname,s.location from section s,"+"faculty t,"+"course c where c.course_id=s.course_id and "+
//				"s.tssn=t.t_ssn and "+"c.course_id="+"?";
		CourseDaoImpl daoImpl= new CourseDaoImpl();
		return daoImpl.getSectList(DB_DRIVER,CONN_URL,USERNAME,PASS,SECTIONS_DATA,crsId);
	}
	
	/**
	 * update the section table and insert it in registration table
	 * @param act
	 * @param rem
	 * @param stssn
	 * @param secId
	 * @param crsId
	 * @return
	 */
	public boolean updateSection(int act, int rem, int stssn, int secId, int crsId) {
		CourseDaoImpl daoImpl= new CourseDaoImpl();
		return daoImpl.updtAndIns(DB_DRIVER,CONN_URL,USERNAME,PASS,UPDATE_SECS,INS_REGS,act,rem,stssn,secId,crsId);
	}


}
