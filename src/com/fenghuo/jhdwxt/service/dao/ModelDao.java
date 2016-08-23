package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Model;

public class ModelDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	//采取单利模式
	private ModelDao (){}    
	public static ModelDao getInstance() {    
		return LazyHolder.INSTANCE;    
	}	
	private static class LazyHolder {    //类加载时创建唯一对象
		private static  ModelDao INSTANCE = new ModelDao();    
	}   
	
	/**
	 * subs_id是子类中的id，根据子类id查询子类下面对应的车型
	 * @param subs_id 子类id
	 * @return 返回的是查询到的车型数组
	 */	
	public List<Model> searchModel(long subs_id){
		List<Model> list=new ArrayList<Model>();
		String sql="select * from "+DBConnect.STAG+"model where subs_id="+subs_id+" order by model_sortkey desc;";
		try {
			con=DBConnect.getConnection();		
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){			
				Model m=new Model();
				m.setId(rs.getLong(1));
				m.setName(rs.getString(2));	
				m.setImg(rs.getString(3));
				m.setSortkey(rs.getString(4));
				m.setSubs_id(rs.getLong(5));
			
				list.add(m);
			}
			close();//查询完毕 关闭所有打开的流
		} catch (SQLException e) { 
			e.printStackTrace();
			return null;
		}		
		return list;		
	}
	public Model  searchCar(long model_id){
		Model m=new Model();
		String sql="select * from "+DBConnect.STAG+"model where model_id="+model_id+";";
		try {
			con=DBConnect.getConnection();		
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){					
				m.setId(rs.getLong(1));
				m.setName(rs.getString(2));	
				m.setImg(rs.getString(3));
				m.setSortkey(rs.getString(4));
				m.setSubs_id(rs.getLong(5));		
				return m;
			}
			close();//查询完毕 关闭所有打开的流
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		return null;
	}
	
	
	
	
	private void close() throws SQLException{
		if(rs  !=null) {rs.close();}
		if(stm !=null) {stm.close();}
		if(pstm!=null) {pstm.close();}
		if(con !=null) {con.close();}
	}
}