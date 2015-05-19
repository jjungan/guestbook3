package com.sds.icto.guestbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sds.icto.guestbook.domain.GuestbookVo;
import com.sds.icto.guestbook.exception.GuestbookDaoException;

@Repository
public class GuestbookDao {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");
		return conn;
	}
	
	public void insertGuestBook(GuestbookVo vo){
		try {
			conn = getConnection();
			String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,?,sysdate)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getMessage());
			stmt.executeUpdate();
			
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new GuestbookDaoException();
		}
	}
	
	public void deleteGuestBook(GuestbookVo vo){
		try {
			conn = getConnection();
			String sql = "delete from guestbook where no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, vo.getNo());
			stmt.executeUpdate();
			
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new GuestbookDaoException();
		}
		
	}
	
	public GuestbookVo selectGuestBookByNo(Long no){
		GuestbookVo vo = new GuestbookVo();
		try {
			conn = getConnection();
			String sql = "select * from guestbook where no = ? order by no desc";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, no);
			rs = stmt.executeQuery();
			while(rs.next()){
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setMessage(rs.getString(4));
				vo.setRegDate(rs.getDate(5));
			}
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new GuestbookDaoException();
		}
		return vo;
	}
	
	public List<GuestbookVo> selectGuestBook(){
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		try {
			conn = getConnection();
			String sql = "select * from guestbook order by no desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setMessage(rs.getString(4));
				vo.setRegDate(rs.getDate(5));
				list.add(vo);
			}
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new GuestbookDaoException();
		}
		return list;
	}
	
	
}
