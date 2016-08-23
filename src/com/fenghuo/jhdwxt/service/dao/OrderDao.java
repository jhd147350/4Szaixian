package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Order;


public class OrderDao {

	private Connection conn;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet re;
	
	public OrderDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static OrderDao getInstance(){
		return LazyHolder.INSTANCE;
	}
	
	private static class LazyHolder{
		private static OrderDao INSTANCE = new OrderDao();
	}
	
	public List<Order> findByType(String uid,String type){
		List<Order> list = new ArrayList<Order>();
		
		try {
			conn = DBConnect.getConnection();
			
			String sql = "select * from "+DBConnect.STAG+"orders where uid = ? and type = ? ;";
//			String sql = "select * from orders";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uid);
			pstm.setString(2, type);
			re=pstm.executeQuery();
			while(re.next()){
				Order order = new Order();
				order.setId(re.getInt(1));
				order.setUid(re.getString(2));
				order.setType(re.getString(3));
				order.setState(re.getString(4));
				order.setName(re.getString(5));
				order.setFeatures(re.getString(6));
				order.setMoney(re.getString(7));
				order.setTime(re.getDate(8));
				System.out.println("order+"+order.toString());
				list.add(order);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public  List<Order> findByState(String uid,String state){
	List<Order> list = new ArrayList<Order>();
		
		try {
			conn = DBConnect.getConnection();
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"orders where state = '"+state+"' and uid = '"+uid+"' ;";
			re = stm.executeQuery(sql);
			while(re.next()){
				Order order = new Order();
				order.setId(re.getInt(1));
				order.setUid(re.getString(2));
				order.setType(re.getString(3));
				order.setState(re.getString(4));
				order.setName(re.getString(5));
				order.setFeatures(re.getString(6));
				order.setMoney(re.getString(7));
				order.setTime(re.getDate(8));
				list.add(order);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	private void close() throws SQLException{
	
		
		if(re!=null){
			re.close();
		}
		if(stm != null){
			stm.close();
		}
		if(pstm != null){
			pstm.close();
		}
		if(conn != null){
			conn.close();
		}
	}
}
