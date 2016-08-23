package com.fenghuo.jhdwxt.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fenghuo.jhdwxt.service.dao.MessageDao;
import com.fenghuo.jhdwxt.service.entity.Messages;

public class MessageServlet extends HttpServlet {

	String coding = "utf-8";
	MessageDao dao = MessageDao.getInstance();
	public MessageServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding(coding);
		response.setCharacterEncoding(coding);
		String action_str = request.getParameter("action");
		int action = Integer.parseInt(action_str);
		System.out.println("--------MessageServlet action-:"+action);
		switch (action) {
		case 1:{
			String uid = request.getParameter("uid");
			System.out.println("--------MessageServlet uid-:"+uid);
			LoadFindByUid(response, uid);
		}
			
			break;

		default:
			break;
		}
	}

	
	public void init() throws ServletException {
		// Put your code here
	}
	
	private void LoadFindByUid(HttpServletResponse response,String uid){
		List<Messages> list = new ArrayList<Messages>();
		list = dao.findByUid(uid);
		JSONArray ja = new JSONArray();
		for(Messages msg:list){
			JSONObject js = new JSONObject();
			js.put("msg_id", msg.getId());
			js.put("msg_uid", msg.getUid());
			js.put("msg_time",msg.getTime());
			js.put("msg_content", msg.getContent());
			ja.add(js);
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(ja.toString());
			System.out.println("---------MessageServlet findByUid-------------");
			System.out.println(ja.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
