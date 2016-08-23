package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Quan;

public class QuanDao {

	private Connection conn;
	private Statement stm;
	private ResultSet rs;

	public QuanDao() {
		// TODO Auto-generated constructor stub
	}
	private static class Instance{
		private static QuanDao INSTANCE = new QuanDao();
	}
	public static QuanDao getInstance(){
		return Instance.INSTANCE;
	}
	
	//通过uid获取券列表
	public List<Quan> findByUid(String uid){
		List<Quan> list = new ArrayList<Quan>();
		conn = DBConnect.getConnection();
		try {
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"quan where uid = '"+uid+"';";
			rs = stm.executeQuery(sql);
			while(rs.next()){
				Quan q = new Quan();
				q.setId(rs.getInt(1));
				q.setUid(rs.getString(2));
				q.setState(rs.getInt(3));
				q.setMoney(rs.getString(4));
				q.setCondition(rs.getString(5));
				q.setDeadline(rs.getDate(6));
				list.add(q);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	private void close() throws SQLException{
		if(rs != null){
			rs.close();
			
		}if(stm != null){
			stm.close();
		}if(conn != null){
			conn.close();
		}
	}
}
