package com.fenghuo.jhdwxt.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fenghuo.jhdwxt.service.dao.QuanDao;
import com.fenghuo.jhdwxt.service.entity.Order;
import com.fenghuo.jhdwxt.service.entity.Quan;

public class QuanServlet extends HttpServlet {

	String coding = "utf-8";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding(coding);
		request.setCharacterEncoding(coding);
		response.setContentType("text/html;charset = utf-8");
		String parameter = request.getParameter("params");
		
		
		String str_action = request.getParameter("action");
		int action = Integer.parseInt(str_action);
		switch (action) {
		case 1: //根据类型获取订单
		{
			
			System.out.println(action+"");
			//JSONObject object = JSONObject.fromObject(parameter);
			
			String uid= request.getParameter("uid");
			
			
	      
            loadQuanByUid(response, uid);
		}
			break;

		
		default:
			break;
		}
	}

	private void loadQuanByUid(HttpServletResponse response,String uid){
		QuanDao dao = new QuanDao();
		List<Quan> list = dao.findByUid(uid);
		JSONArray ja = new JSONArray();
		for(Quan q:list){
			JSONObject js = new JSONObject();
		    js.put("quan_id", q.getId());
		    js.put("quan_uid", q.getUid());
		    js.put("quan_state", q.getState());
		    js.put("quan_money", q.getMoney());
		    js.put("quan_condition", q.getCondition());
		    
		    js.put("quan_deadline", q.getDeadline().toString());
			ja.add(js);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(ja.toString());
			System.out.println(ja.toString());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("OrderSrevlet.loadOrderByType()--异常");
		}
	}
}
