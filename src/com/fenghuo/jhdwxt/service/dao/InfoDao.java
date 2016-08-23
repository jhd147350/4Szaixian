package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Info;

public class InfoDao {

	private Connection conn;
	private Statement stm;
	private ResultSet re;
	private PreparedStatement pstm;
	public InfoDao(){
		
	}
	public static InfoDao getInstance(){
		return instance.INSTANCE;
	}
	private static class instance{
		private static InfoDao INSTANCE = new InfoDao();
	}
	
	//全部资讯
	public List<Info> findAll(){
		List<Info> list = new ArrayList<Info>();
		conn = DBConnect.getConnection();
		try {
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"info";
			re = stm.executeQuery(sql);
			while(re.next()){
				Info info = new Info();
				info.setId(re.getInt(1));
				info.setInfoTitle(re.getString(2));
				info.setInfoTime(re.getString(3));
				info.setInfoContent(re.getString(4));
				info.setInfoImg(re.getString(5));
				info.setInfoType(re.getString(6));
				list.add(info);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//根据标签获取资讯
	public List<Info> findByType(String type){
		List<Info> list = new ArrayList<Info>();
		
		conn = DBConnect.getConnection();
		try {
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"info where infoType = '"+type+"';";
			re = stm.executeQuery(sql);
			while(re.next()){
				Info info = new Info();
				info.setId(re.getInt(1));
				info.setInfoTitle(re.getString(2));
				info.setInfoTime(re.getString(3));
				info.setInfoContent(re.getString(4));
				info.setInfoImg(re.getString(5));
				info.setInfoType(re.getString(6));
				list.add(info);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return list;
	}
	//添加资讯
		public boolean insertInfo(Info info){
			conn =  DBConnect.getConnection();
			String sql = "insert into "+DBConnect.STAG+"info (infoTitle,infoTime,infoContent,infoImg,infoType) values (?,?,?,?,?);";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, info.getInfoTitle());
				pstm.setString(2, info.getInfoTime());
				pstm.setString(3, info.getInfoContent());
				pstm.setString(4, info.getInfoImg());
				pstm.setString(5, info.getInfoType());
				pstm.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
	private void close() throws SQLException{
		if(re != null){
			re.close();
		}
		if(pstm!=null){
			pstm.close();
		}
		if(stm != null){
			stm.close();
		}
		if(conn != null){
			conn.close();
		}
	}
}
