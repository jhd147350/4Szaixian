package com.fenghuo.jhdwxt.service.servlet;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fenghuo.jhdwxt.service.dao.UserDao;
import com.fenghuo.jhdwxt.service.entity.User;

import net.sf.json.JSONObject;

public class UserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	
		
		System.out.println("---------------------UserServlet:doGet---------------");
		String str_action =request.getParameter("action");
		int action=Integer.parseInt(str_action);
		switch (action) {
		case 1://±íÊ¾µÇÂ¼
			String phonenum=request.getParameter("phonenum");
			String password=request.getParameter("password");
			//JSONObject jo=new JSONObject(loginInfo);			
			Login(phonenum,password,response);			
			break;
		case 2:
			String sphonenum=request.getParameter("phonenum");
			String spassword=request.getParameter("password");		
			System.out.println("---------------------UserServlet:doGet---×¢²á");
			Signup(sphonenum,spassword,response);	
		default:
			break;
		}

	}

	private void Signup(String phonenum, String password,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserDao ud=UserDao.getInstance();
		JSONObject jo=new JSONObject();	
		try {
			User user=new User();
			user.setPhonenum(phonenum);
			user.setPassword(password);
			user.setNickname("»úÆ÷ÈË");
			boolean isSuc = ud.Signup(user);			
			jo.put("isSuc", isSuc);
			System.out.println("-------------------------×¢²á"+isSuc+"---------------");
			PrintWriter pw = response.getWriter();
			pw.println(jo.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------------------×¢²áÒì³£---------------");
		}				
		
	}

	private void Login(String phonenum,String password,HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserDao ud=UserDao.getInstance();
		JSONObject jo=new JSONObject();	
		try {
			boolean isSuc = ud.Login(phonenum, password);			
			jo.put("isSuc", isSuc);
			System.out.println("-------------------------µÇÂ¼"+isSuc+"---------------");
			PrintWriter pw = response.getWriter();
			pw.println(jo.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------------------µÇÂ¼Òì³£---------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------------------µÇÂ¼Òì³£---------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------------------µÇÂ¼Òì³£---------------");
		}				
	}


}
