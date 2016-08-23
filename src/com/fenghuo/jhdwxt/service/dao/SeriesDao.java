package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Series;

public class SeriesDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	//采取单利模式
	private SeriesDao (){}    
	public static SeriesDao getInstance() {    
		return LazyHolder.INSTANCE;    
	}	
	private static class LazyHolder {    //类加载时创建唯一对象
		private static  SeriesDao INSTANCE = new SeriesDao();
		
	}   
	
	
	
	public List<Series> searchSeries(long brand_id){
		List<Series> list=new ArrayList<Series>();
		String sql="select * from "+DBConnect.STAG+"series where brand_id="+brand_id+";";
		try {
			con=DBConnect.getConnection();		
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){			
				Series s=new Series();
				s.setId(rs.getLong(1));
				s.setName(rs.getString(2));	
				s.setBrand_id(rs.getLong(3));
				list.add(s);
			}
			close();//查询完毕 关闭所有打开的流
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return list;
		
	}
	
	
	
	
	private void close() throws SQLException{
		if(rs  !=null) {rs.close();}
		if(stm !=null) {stm.close();}
		if(pstm!=null) {pstm.close();}
		if(con !=null) {con.close();}
	}
}
