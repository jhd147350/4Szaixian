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
	
	
	//��ȡ����ģʽ
	private ModelDao (){}    
	public static ModelDao getInstance() {    
		return LazyHolder.INSTANCE;    
	}	
	private static class LazyHolder {    //�����ʱ����Ψһ����
		private static  ModelDao INSTANCE = new ModelDao();    
	}   
	
	/**
	 * subs_id�������е�id����������id��ѯ���������Ӧ�ĳ���
	 * @param subs_id ����id
	 * @return ���ص��ǲ�ѯ���ĳ�������
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
			close();//��ѯ��� �ر����д򿪵���
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
			close();//��ѯ��� �ر����д򿪵���
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