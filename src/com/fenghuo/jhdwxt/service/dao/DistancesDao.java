package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Distances;
import com.fenghuo.jhdwxt.service.entity.Recard;




public class DistancesDao {
	Connection conn;
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;
	public DistancesDao(){
		
	}
	private static class reInstance{
		private static DistancesDao INSTANCE = new DistancesDao();
	}
	public static DistancesDao getInstance(){
		return reInstance.INSTANCE;
	}
	//根据uid和类型获取列表
		public List<Distances> findByUid(String uid){
			List<Distances> list = new ArrayList<Distances>();
			
			 conn = DBConnect.getConnection();
			try {
				 stm = conn.createStatement();
				String sql = "select * from "+DBConnect.STAG+"distances where uid = '"+uid+"';";
				 rs = stm.executeQuery(sql);
				while(rs.next()){
					Distances dis = new Distances();
					dis.setId(rs.getInt(1));
					dis.setUid(rs.getString(2));
					dis.setType(rs.getString(3));
					dis.setDistancess(rs.getDouble(4));
					list.add(dis);
				}
				close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		
		//添加distances数据
		public boolean insertDistance(Distances dis){
			String sql = "insert into "+DBConnect.STAG+"distances(uid,type,distancess) values(?,?,?)";
			try {
				conn=DBConnect.getConnection();
				pstm = conn.prepareStatement(sql);
				pstm.setString( 1, dis.getUid());
				pstm.setString( 2, dis.getType());
				pstm.setDouble(3, dis.getDistancess());
				pstm.execute();
				System.out.println("DistancesDao re :" +dis);
				close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		private void close() throws SQLException{
			
			
			if(rs!=null){
				rs.close();
			}
			if(stm != null){
				stm.close();
			}if(pstm != null){
				pstm.close();
			}
			if(conn != null){
				conn.close();
			}
		}
}
