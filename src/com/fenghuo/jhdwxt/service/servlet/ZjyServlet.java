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

import com.fenghuo.jhdwxt.service.dao.ZjyitemDao;
import com.fenghuo.jhdwxt.service.entity.Zjyitem;

public class ZjyServlet extends HttpServlet {


    private ZjyitemDao dao = ZjyitemDao.getIntance();
    List<Zjyitem> list;

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String actionStr = request.getParameter("action");
		int action = Integer.parseInt(actionStr);
		System.out.println("-------zjy action "+action);
		switch (action) {
		case 1:{
			String addressStr =  request.getParameter("address");
			String address = new String(addressStr.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println("-------zjyservlet address --"+address);
			loadFindByAddress(response, address);
		}
			
			break;

		case 2:{
			loadFindByAll(response);
		}
		default:
			break;
		}
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

	private void loadFindByAddress(HttpServletResponse response,String address){
		list = new ArrayList<Zjyitem>();
		list = dao.findByAddress(address);
		JSONArray ja = new JSONArray();
		for(Zjyitem z:list){
			JSONObject js = new JSONObject();
			js.put("zjy_id", z.getId());
			js.put("zjy_title", z.getZjyTitle());
			js.put("zjy_content", z.getZjyContent());
			js.put("zjy_cfd", z.getZjyCfd());
			js.put("zjy_time", z.getZjyTime());
			js.put("zjy_address", z.getZjyAddress());
			js.put("zjy_img", z.getZjyImg());
			js.put("zjy_http", z.getZjyHttp());
			ja.add(js);
		}
		try {
			PrintWriter out = response.getWriter();
			out.println(ja.toString());
			System.out.println("----loadFindByAddress  ja  :"+ja.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void loadFindByAll(HttpServletResponse response){
		list = new ArrayList<Zjyitem>();
		list = dao.findByAll();
		JSONArray ja = new JSONArray();
		for(Zjyitem z:list){
			JSONObject js = new JSONObject();
			js.put("zjy_id", z.getId());
			js.put("zjy_title", z.getZjyTitle());
			js.put("zjy_content", z.getZjyContent());
			js.put("zjy_cfd", z.getZjyCfd());
			js.put("zjy_time", z.getZjyTime());
			js.put("zjy_address", z.getZjyAddress());
			js.put("zjy_img", z.getZjyImg());
			js.put("zjy_http", z.getZjyHttp());
			ja.add(js);
		}
		try {
			PrintWriter out = response.getWriter();
			out.println(ja.toString());
			System.out.println("----loadFindByAll  ja  :"+ja.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
