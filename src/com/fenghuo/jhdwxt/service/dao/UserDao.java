package com.fenghuo.jhdwxt.service.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.fenghuo.jhdwxt.service.db.DBConnect;
import com.fenghuo.jhdwxt.service.entity.User;

public class UserDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;

	//采取单利模式
	private UserDao (){}    
	public static UserDao getInstance() {    
		return LazyHolder.INSTANCE;    
	}	
	private static class LazyHolder {    //类加载时创建唯一对象
		private static  UserDao INSTANCE = new UserDao();    
	}

	public boolean Login(String phonenum,String password) throws ClassNotFoundException, SQLException{
		//	List<User> list=new ArrayList<User>();
		con=DBConnect.getConnection();
		//	User user=new User();
		if(con!=null){
			stm = con.createStatement();
			String sql="select * from "+DBConnect.STAG+"user where phonenum='"+phonenum+"' and password='"+password+"';";
			rs = stm.executeQuery(sql);
			if(rs.next()){			
				if(!rs.next()){
					close();
					return true;
				}
			}
		}
		return false;
	}
	public boolean Signup(User user)  {
		String sql = "insert into "+DBConnect.STAG+"user(phonenum,password,nickname) values(?,?,?)";
		try {
			con=DBConnect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString( 1, user.getPhonenum());
			pstm.setString( 2, user.getPassword());
			pstm.setString( 3, user.getNickname());
			pstm.execute();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private void close() throws SQLException{
		if(rs  !=null) {rs.close();}
		if(stm !=null) {stm.close();}
		if(pstm!=null) {pstm.close();}
		if(con !=null) {con.close();}
	}


}
