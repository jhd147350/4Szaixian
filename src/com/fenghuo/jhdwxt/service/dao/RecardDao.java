package com.fenghuo.jhdwxt.service.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.Recard;

public class RecardDao {
	private Connection conn;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;

	public RecardDao() {
		// TODO Auto-generated constructor stub
	}
	private static class reInstance{
		private static RecardDao INSTANCE = new RecardDao();
	}
	public static RecardDao getInstance(){
		return reInstance.INSTANCE;
	}
	
	//根据uid获取recard
	public List<Recard> findByUid(String uid){
		List<Recard> list = new ArrayList<Recard>();
		
		conn = DBConnect.getConnection();
		try {
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"recard where uid = '"+uid+"';";
			rs = stm.executeQuery(sql);
			while(rs.next()){
				Recard re = new Recard();
				re.setId(rs.getInt(1));
				re.setUid(rs.getString(2));
				re.setXcType(rs.getString(3));
				re.setXcMoney(rs.getDouble(4));
				re.setXcTime(rs.getString(5));
				re.setXcMonth(rs.getString(6));
				re.setXcYear(rs.getString(7));
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//根据日期获取list
	public List<Recard> findByDate(String uid,String month) {
		List<Recard> list = new ArrayList<Recard>();
	
		conn = DBConnect.getConnection();
		try {
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"recard where uid = ? and xcMonth = ?;";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uid);
			pstm.setString(2, month);
			rs=pstm.executeQuery();
			while(rs.next()){
				Recard re = new Recard();
				re.setId(rs.getInt(1));
				re.setUid(rs.getString(2));
				re.setXcType(rs.getString(3));
				re.setXcMoney(rs.getDouble(4));
				re.setXcTime(rs.getString(5));
				re.setXcMonth(rs.getString(6));
				re.setXcYear(rs.getString(7));
				list.add(re);
				System.out.println("RecardDao month recard:"+re.toString());
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//添加recard
	public boolean insertRecard(Recard re){
		String sql = "insert into "+DBConnect.STAG+"recard(uid,xcType,xcMoney,xcTime,xcMonth,xcYear) values(?,?,?,?,?,?)";
		try {
			conn=DBConnect.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString( 1, re.getUid());
			pstm.setString( 2, re.getXcType());
			pstm.setDouble( 3, re.getXcMoney());
			pstm.setString(4, re.getXcTime());
			pstm.setString(5, re.getXcMonth());
			pstm.setString(6, re.getXcYear());
			pstm.execute();
			System.out.println("RecardDao re :" +re);
			close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//根据年份查找recard
	public List<Recard> findByYear(String uid,String year){
		List<Recard> list = new ArrayList<Recard>();
		
		conn = DBConnect.getConnection();
		try {
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"recard where uid = ? and xcYear = ?;";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uid);
			pstm.setString(2, year);
			rs=pstm.executeQuery();
			while(rs.next()){
				Recard re = new Recard();
				re.setId(rs.getInt(1));
				re.setUid(rs.getString(2));
				re.setXcType(rs.getString(3));
				re.setXcMoney(rs.getDouble(4));
				re.setXcTime(rs.getString(5));
				re.setXcMonth(rs.getString(6));
				re.setXcYear(rs.getString(7));
				list.add(re);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//按年份和类型查找
	public List<Recard> findByYearAndType(String uid,String year,String type){
		List<Recard> list = new ArrayList<Recard>();
		
		conn = DBConnect.getConnection();
		try {
			stm = conn.createStatement();
			String sql = "select * from "+DBConnect.STAG+"recard where uid = ? and xcYear = ? and xcType = ?;";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uid);
			pstm.setString(2, year);
			pstm.setString(3, type);
			rs=pstm.executeQuery();
			while(rs.next()){
				Recard re = new Recard();
				re.setId(rs.getInt(1));
				re.setUid(rs.getString(2));
				re.setXcType(rs.getString(3));
				re.setXcMoney(rs.getDouble(4));
				re.setXcTime(rs.getString(5));
				re.setXcMonth(rs.getString(6));
				re.setXcYear(rs.getString(7));
				list.add(re);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//按月份和类型查找
		public List<Recard> findByMonthAndType(String uid,String month,String type){
			List<Recard> list = new ArrayList<Recard>();
			
			conn = DBConnect.getConnection();
			try {
				stm = conn.createStatement();
				String sql = "select * from "+DBConnect.STAG+"recard where uid = ? and xcMonth = ? and xcType = ?;";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, uid);
				pstm.setString(2, month);
				pstm.setString(3, type);
				rs=pstm.executeQuery();
				while(rs.next()){
					Recard re = new Recard();
					re.setId(rs.getInt(1));
					re.setUid(rs.getString(2));
					re.setXcType(rs.getString(3));
					re.setXcMoney(rs.getDouble(4));
					re.setXcTime(rs.getString(5));
					re.setXcMonth(rs.getString(6));
					re.setXcYear(rs.getString(7));
					list.add(re);
				}
				close();
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
