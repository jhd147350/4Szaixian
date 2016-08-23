package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Brand;

public class BrandDao {
	
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	//采取单利模式
	private BrandDao (){}    
	public static BrandDao getInstance() {    
		return LazyHolder.INSTANCE;    
	}	
	private static class LazyHolder {    //类加载时创建唯一对象
		private static  BrandDao INSTANCE = new BrandDao();  
		
	}


	public List<Brand> searchAll(){
		List<Brand> list=new ArrayList<Brand>();
		try {
			con=DBConnect.getConnection();		
			stm = con.createStatement();
			String sql="select * from "+DBConnect.STAG+"brand order by brand_sortkey;";
			rs = stm.executeQuery(sql);
			while(rs.next()){			
				Brand b=new Brand();
				b.setId(rs.getLong(1));
				b.setName(rs.getString(2));
				b.setImg(rs.getString(3));
				b.setSortkey(rs.getString(4));
				list.add(b);
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
