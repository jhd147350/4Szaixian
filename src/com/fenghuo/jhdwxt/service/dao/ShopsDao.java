package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Shops;

public class ShopsDao {
    List<Shops> list ;
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	private ShopsDao() {
		// TODO Auto-generated constructor stub
	}
	 private static class Shopsholder{
		 private static ShopsDao INSTANCE = new ShopsDao();
	 }
	 public static ShopsDao getInstance(){
		 return Shopsholder.INSTANCE;
	 }
	
	 //根据车型车系具体车和保养类型查找
	 public List<Shops> findShops (String brand,String series,String model,String type) throws SQLException{
		 list = new ArrayList<Shops>();
		 String sql = "select * from "+DBConnect.STAG+"shops where brand = ? and series = ? and model = ? and type = ? ;";
		 conn = DBConnect.getConnection();
		 pstm = conn.prepareStatement(sql);
		
		 pstm.setString(1, brand);
		 pstm.setString(2, series);
		 pstm.setString(3, model);
		 pstm.setString(4, type);
		 rs = pstm.executeQuery();
		 while(rs.next()){
			 Shops s = new Shops();
			 s.setId(rs.getInt(1));
			 s.setBrand(rs.getString(2));
			 s.setSeries(rs.getString(3));
			 s.setModel(rs.getString(4));
			 s.setType(rs.getString(5));
			 s.setShopName(rs.getString(6));
			 s.setShopYuyue(rs.getString(7));
			 s.setShop4S(rs.getString(8));
			 s.setShopDistance(rs.getString(9));
			 s.setShopAddress(rs.getString(10));
			 s.setShopImg(rs.getString(11));
			 list.add(s);			 
		 }
		 close();
		 System.out.println("---------ShopDao find-------");
		 System.out.println(list.toString());
		 return list;
	 }

	 private void close() throws SQLException{
		 if(rs != null){
			 rs.close();
		 }if(pstm != null){
			 pstm.close();
		 }if(conn != null){
			 conn.close();
		 }
	 }
}
