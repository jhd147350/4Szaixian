package com.fenghuo.jhdwxt.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fenghuo.jhdwxt.service.dao.OrderDao;
import com.fenghuo.jhdwxt.service.entity.Order;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrderServlet extends HttpServlet {

	String coding = "utf-8";
	public OrderServlet() {
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
			String type = request.getParameter("type");
			String s1=new String(type.getBytes("ISO-8859-1"), "utf-8");
	
			loadOrderByType(response, uid, s1);
//			String result = "chenggong";
//			out.print(result);
			
		}
			break;

		case 2://根据订单状态获取订单
		{
           
			
			String uid= request.getParameter("uid");
			String state = request.getParameter("state");
			
//			String  s=new String(uid.getBytes("ISO-8859-1"), "utf-8");
			
			String s1=new String(state.getBytes("ISO-8859-1"), "utf-8");
			
			loadOrderByState(response, uid, s1);
			
		}
		default:
			break;
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}
	
	private void loadOrderByType(HttpServletResponse response,String uid,String type){
		OrderDao dao = OrderDao.getInstance();
		List<Order> list = dao.findByType(uid, type);
		JSONArray ja = new JSONArray();
		for(Order o:list){
			JSONObject js = new JSONObject();
			js.put("order_id", o.getId());
			js.put("order_uid", o.getUid());
			js.put("order_type", o.getType());
			js.put("order_state", o.getState());
			js.put("order_name", o.getName());
			js.put("order_features", o.getFeatures());
			js.put("order_money", o.getMoney());
//			js.put("order_time", o.getTime());
			js.put("order_time", o.getTime().toString());
			ja.add(js);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(ja.toString());
			System.out.println(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("OrderSrevlet.loadOrderByType()--异常");
		}
	}
	private void loadOrderByState(HttpServletResponse response,String uid,String state){
		OrderDao dao = OrderDao.getInstance();
		List<Order> list = dao.findByState(uid, state);
		JSONArray ja = new JSONArray();
		for(Order o:list){
			JSONObject js = new JSONObject();
			js.put("order_id", o.getId());
			js.put("order_uid", o.getUid());
			js.put("order_type", o.getType());
			js.put("order_state", o.getState());
			js.put("order_name", o.getName());
			js.put("order_features", o.getFeatures());
			js.put("order_money", o.getMoney());
			js.put("order_time", o.getTime().toString());
			ja.add(js);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(ja.toString());
			System.out.println(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("OrderSrevlet.loadOrderByState()--异常");
		}
	}

}
