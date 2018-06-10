package com.reg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reg.beans.CourseBean;
import com.reg.beans.SectionBean;
import com.reg.beans.Student;


public class CourseDaoImpl {

	private Connection getConnection(String dbDriver, String connectionUrl, String userName, String password) {
		System.out.println("DB connection...");
		Connection connection=null;	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(connectionUrl, userName, password);
			if(!connection.equals(null)){
				System.out.println(" Connection Success.");
				return connection;		
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return connection;
	}



	public boolean insertStudent(String dbDriver, String connUrl, String username, String pass, String regQuery,
			Student student) {

		PreparedStatement pstmt = null;
		int result=0;
		Connection conn = getConnection(dbDriver,connUrl,username,pass);
		try {
			//"INSERT INTO STUDENT"+ "(STSSN,SNAME,PASSWORD,DOB,EMAILID,MAJOR,ADDRESS) VALUES"+ "(?,?,?,?,?,?,?)";
			pstmt= conn.prepareStatement(regQuery);
			pstmt.setInt(1, student.getStssn());
			pstmt.setString(2, student.getSname());
			pstmt.setString(3, student.getPassword());
			pstmt.setString(4, student.getDob());
			pstmt.setString(5, student.getEmailid());
			pstmt.setString(6, student.getMajor());
			pstmt.setString(7, student.getAddress());
			result=	pstmt.executeUpdate();
			if(result!=0){
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {

			closeAll(conn,pstmt);
		}

		return false;
	}


	private void closeAll(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}


	}


	public Student getCreds(String dbDriver, String connUrl, String username, String pass, String getCreds,
			String username2) {
		PreparedStatement pstmt = null;
		Student student=null;
		ResultSet rs=null;
		Connection conn = getConnection(dbDriver,connUrl,username,pass);

		//"SELECT stssn,sname,password,dob,emailid,major,address from student where sname = "+"?";
		try {
			pstmt= conn.prepareStatement(getCreds);
			pstmt.setString(1, username2);
			rs= pstmt.executeQuery();
			if(!rs.equals(null)){
				while(rs.next()){
					System.out.println(rs.getString(1)+" "+rs.getString(2));
					student= new Student();
					student.setStssn(rs.getInt(1));
					student.setSname(rs.getString(2));
					student.setPassword(rs.getString(3));
					student.setDob(rs.getString(4));
					student.setEmailid(rs.getString(5));
					student.setMajor(rs.getString(6));
					student.setAddress(rs.getString(7));


				}
				System.out.println(">>>>>>> "+student);
				return student;
			}else
				return student;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				closeAll(conn, pstmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return student;
	}


	/**
	 * fetch course list from db
	 * @param dbDriver
	 * @param connUrl
	 * @param username
	 * @param pass
	 * @param getCorsList
	 * @param option
	 * @return
	 */
	public List<CourseBean> getCrsList(String dbDriver, String connUrl, String username, String pass,
			String getCorsList, String option) {
		//"select course_id, c_name, description from course where department ="+"?";
		ResultSet resultSet= null;
		PreparedStatement pstmt=null;
		Connection connection= getConnection(dbDriver, connUrl, username, pass);
		List<CourseBean> crList= new ArrayList<CourseBean>();
		if(connection!=null){
			try {
				pstmt=connection.prepareStatement(getCorsList);
				pstmt.setString(1,option);
				resultSet=pstmt.executeQuery();
				if(!resultSet.equals(null)){
					System.out.println("resultset is not empty");
					while (resultSet.next()) {
						CourseBean crBean= new CourseBean();
						crBean.setCourseid(resultSet.getInt(1));
						crBean.setCname(resultSet.getString(2));
						crBean.setDesc(resultSet.getString(3));
						crList.add(crBean);						
					}
					if(!crList.isEmpty()){
						return crList;
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					resultSet.close();
					closeAll(connection, pstmt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		return null;
	}


	/**
	 * fETCH Section data from DB
	 * @param dbDriver
	 * @param connUrl
	 * @param username
	 * @param pass
	 * @param sectionsData
	 * @param crsId
	 * @return
	 */
	public List<SectionBean> getSectList(String dbDriver, String connUrl, String username, String pass,
			String sectionsData, int crsId) {
		System.out.println("Query: "+sectionsData);
		//select s.s_no,s.course_id,c.c_name,s.semester,s.year,s.capacity,s.actual,s.rem,"+
		//"t.tname,s.location from section s,"+"faculty t,"+"course c where c.course_id=s.course_id and "+
		//"s.tssn=t.t_ssn and "+"c.course_id="+"?";		
		ResultSet resultSet= null;
		PreparedStatement pstmt=null;
		Connection connection= getConnection(dbDriver, connUrl, username, pass);
		List<SectionBean> secList= new ArrayList<SectionBean>();
		if(connection!=null){
			try {
				pstmt=connection.prepareStatement(sectionsData);
				pstmt.setInt(1, crsId);
				resultSet=pstmt.executeQuery();
				if(!resultSet.equals(null)){
					System.out.println("resultset is not empty");
					while (resultSet.next()) {
						SectionBean sectionBean= new SectionBean();
						sectionBean.setSno(resultSet.getInt(1));
						sectionBean.setCourseid(resultSet.getInt(2));
						sectionBean.setTitle(resultSet.getString(3));
						sectionBean.setSem(resultSet.getString(4));
						sectionBean.setYear(resultSet.getString(5));
						sectionBean.setCapacity(resultSet.getInt(6));
						sectionBean.setAct(resultSet.getInt(7));
						sectionBean.setRem(resultSet.getInt(8));
						sectionBean.setInstructor(resultSet.getString(9));
						sectionBean.setLocation(resultSet.getString(10));
						secList.add(sectionBean);										
					}
					if(!secList.isEmpty()){
						return secList;
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					resultSet.close();
					closeAll(connection, pstmt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		return null;
	}



	public boolean updtAndIns(String dbDriver, String connUrl, String username, String pass, String updateSecs,
			String insRegs, int act, int rem, int stssn, int secId, int crsId) {
		PreparedStatement pstmt = null;
		int result=0;
		int r1=0;
		Connection conn = getConnection(dbDriver,connUrl,username,pass);
		try {
			//"UPDATE section SET actual ="+" ?"+" , rem="+"?"+" WHERE s_no ="+"?";
			//"INSERT INTO csregister"+ "(stssn,s_no,course_id) VALUES"+ "(?,?,?)";
			pstmt= conn.prepareStatement(updateSecs);
			pstmt.setInt(1, act);
			pstmt.setInt(2, rem);
			pstmt.setInt(3, secId);
			result=	pstmt.executeUpdate();
			if(result!=0){

				pstmt=conn.prepareStatement(insRegs);
				pstmt.setInt(1, stssn);
				pstmt.setInt(2, secId);
				pstmt.setInt(3,crsId);
				r1=pstmt.executeUpdate();
				if(r1!=0){
					return true;	
				}else{
					return false;
				}

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {

			closeAll(conn,pstmt);
		}

		return false;
	}




}
