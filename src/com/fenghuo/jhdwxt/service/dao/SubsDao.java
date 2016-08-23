package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Subs;

public class SubsDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	//��ȡ����ģʽ
	private SubsDao (){}    
	public static SubsDao getInstance() {    
		return LazyHolder.INSTANCE;    
	}	
	private static class LazyHolder {    //�����ʱ����Ψһ����
		private static  SubsDao INSTANCE = new SubsDao();    
	}   
	
	
	
	public List<Subs> searchSubs(long series_id){
		List<Subs> list=new ArrayList<Subs>();
		String sql="select * from "+DBConnect.STAG+"subs where series_id="+series_id+";";
		try {
			con=DBConnect.getConnection();		
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){			
				Subs s=new Subs();
				s.setId(rs.getLong(1));
				s.setName(rs.getString(2));	
				s.setSeries_id(rs.getLong(3));
				list.add(s);
			}
			close();//��ѯ��� �ر����д򿪵���
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
