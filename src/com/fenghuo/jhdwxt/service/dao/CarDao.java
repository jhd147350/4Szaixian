package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Car;

public class CarDao {
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	private CarDao() {
		// TODO Auto-generated constructor stub
	}
	
	private static class CarInstence{
		private static CarDao INSTANCE = new CarDao();
	}
	
	public static CarDao getInstance (){
		return CarInstence.INSTANCE;
	}
	
	//根据uid查找car
	public Car findByUid(String uid){
		Car c  = new Car();
		conn = DBConnect.getConnection();
		String sql = "select * from "+DBConnect.STAG+"cars where uid = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uid);
			rs = pstm.executeQuery();
			while(rs.next()){
				
				c.setId(rs.getInt(1));
				c.setUid(rs.getString(2));
				c.setBrand(rs.getString(3));
				c.setSeries(rs.getString(4));
				c.setModel(rs.getString(5));
				c.setImg(rs.getString(6));
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
//添加car
	public boolean insertCar(Car car){
		String sql = "insert into "+DBConnect.STAG+"cars(uid,brand,series,model) values(?,?,?,?);";
		conn = DBConnect.getConnection();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, car.getUid());
			pstm.setString(2, car.getBrand());
			pstm.setString(3, car.getSeries());
			pstm.setString(4, car.getModel());
			
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//根据uid修改car
	public boolean updateCar(String uid,String brand,String series,String model,String img){
		String sql = "update "+DBConnect.STAG+"cars set brand = ?,series = ?,model = ? ,img = ? where uid =?;";
		conn = DBConnect.getConnection();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, brand);
			pstm.setString(2, series);
			pstm.setString(3, model);
			pstm.setString(4, img);
			pstm.setString(5, uid);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private void close() throws SQLException{
		if(rs!=null){
			rs.close();
		}if(pstm!=null){
			pstm.close();
		}if(conn != null){
			conn.close();
		}
	}
}
