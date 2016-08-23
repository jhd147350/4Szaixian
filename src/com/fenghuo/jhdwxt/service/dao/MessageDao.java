package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Messages;

public class MessageDao {

	private Connection conn;
	private Statement stm;
	private ResultSet re;

	public MessageDao() {
		// TODO Auto-generated constructor stub
	}
	private static class instance {
		private static MessageDao INSTANCE = new MessageDao();
	}
	public static MessageDao getInstance(){
		return instance.INSTANCE;
	}
	
	//根据uid获取消息
	public List<Messages> findByUid(String uid){
		List<Messages> list =  new ArrayList<Messages>();
		conn = DBConnect.getConnection();
		try {
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"messages where uid = '"+uid+"';";
			re = stm.executeQuery(sql);
			while(re .next()){
				Messages msg = new Messages();
				msg.setId(re.getInt(1));
				msg.setUid(re.getString(2));
				msg.setTime(re.getString(3));
				msg.setContent(re.getString(4));
				list.add(msg);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	private void close() throws SQLException{
		if(re != null){
			re.close();
		}if(stm != null){
			stm.close();
		}if(conn != null){
			conn.close();
		}
	}
}
