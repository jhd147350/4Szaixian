package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Zjyitem;

public class ZjyitemDao {

	List<Zjyitem> list;
	private Connection conn;
	private PreparedStatement ptsm;
	private ResultSet rs;
	private ZjyitemDao() {
		// TODO Auto-generated constructor stub
	}
	private static class zjyIntence{
		private static ZjyitemDao INSTANCE = new ZjyitemDao();
	}
	public static ZjyitemDao getIntance(){
		return zjyIntence.INSTANCE;
	}
	
	public List<Zjyitem> findByAddress(String address){
		list = new ArrayList<Zjyitem>();
		String sql = "select * from "+DBConnect.STAG+"zjyitem where zjyAddress = ?;";
		conn = DBConnect.getConnection();
		try {
			ptsm = conn.prepareStatement(sql);
			ptsm.setString(1, address);
			rs = ptsm.executeQuery();
			while(rs.next()){
				Zjyitem z = new Zjyitem();
				z.setId(rs.getInt(1));
				z.setZjyTitle(rs.getString(2));
				z.setZjyContent(rs.getString(3));
				z.setZjyCfd(rs.getString(4));
				z.setZjyTime(rs.getString(5));
				z.setZjyAddress(rs.getString(6));
				z.setZjyImg(rs.getString(7));
				z.setZjyHttp(rs.getString(8));
				list.add(z);
			}
			close();
			System.out.println("---------zjydao zjy"+list.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<Zjyitem> findByAll(){
		list = new ArrayList<Zjyitem>();
		String sql = "select * from "+DBConnect.STAG+"zjyitem ;";
		conn = DBConnect.getConnection();
		try {
			ptsm = conn.prepareStatement(sql);
			
			rs = ptsm.executeQuery();
			while(rs.next()){
				Zjyitem z = new Zjyitem();
				z.setId(rs.getInt(1));
				z.setZjyTitle(rs.getString(2));
				z.setZjyContent(rs.getString(3));
				z.setZjyCfd(rs.getString(4));
				z.setZjyTime(rs.getString(5));
				z.setZjyAddress(rs.getString(6));
				z.setZjyImg(rs.getString(7));
				z.setZjyHttp(rs.getString(8));
				list.add(z);
			}
			close();
			System.out.println("---------zjydao zjy"+list.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	private void close() throws SQLException{
	
		
		if(rs!=null){
			rs.close();
		}
	
		if(ptsm != null){
			ptsm.close();
		}
		if(conn != null){
			conn.close();
		}
	}
}
