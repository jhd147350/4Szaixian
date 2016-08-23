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

import com.fenghuo.jhdwxt.service.dao.InfoDao;
import com.fenghuo.jhdwxt.service.entity.Info;

public class InfoServlet extends HttpServlet {

	InfoDao dao = InfoDao.getInstance();
	List<Info> list;
	public InfoServlet() {
		super();
	}

	
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
		System.out.println("InfoServlet action :"+action);
		switch (action) {
		case 1:{
			LoadFindAll(response);
			
		}
			
			break;
        case 2:{
        	String typeStr = request.getParameter("type");
        	String type = new String(typeStr.getBytes("ISO-8859-1"),"UTF-8");
        	LoadFindByType(response, type);
        }
			
			break;
        case 3:{
        	Info info = new Info();
        	String titleStr = request.getParameter("title");
        	String timeStr = request.getParameter("time");
        	String contentStr = request.getParameter("content");
        	String img = request.getParameter("img");
        	String typeStr = request.getParameter("type");
        	String title = new String(titleStr.getBytes("ISO-8859-1"),"UTF-8");
        	String time = new String(timeStr.getBytes("ISO-8859-1"),"UTF-8");
        	String content = new String(contentStr.getBytes("ISO-8859-1"),"UTF-8");
        	String type = new String(typeStr.getBytes("ISO-8859-1"),"UTF-8");
        	info.setInfoTitle(title);
        	info.setInfoTime(time);
        	info.setInfoContent(content);
        	info.setInfoImg(img);
        	info.setInfoType(type);
        	LoadInsertInfo(response, info);
        	
        }
	
	        break;

		default:
			break;
		}
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}
  //查询全部
	private void LoadFindAll(HttpServletResponse response){
		list = new ArrayList<Info>();
		list = dao.findAll();
		JSONArray ja = new JSONArray();
		for(Info i:list){
			JSONObject js = new JSONObject();
			js.put("info_id", i.getId());
			js.put("info_title", i.getInfoTitle());
			js.put("info_time", i.getInfoTime());
			js.put("info_content", i.getInfoContent());
			js.put("info_img", i.getInfoImg());
			js.put("info_type", i.getInfoType());
			ja.add(js);
		}
		try {
			PrintWriter out = response.getWriter();
			out.println(ja.toString());
			System.out.println("----------LoadFindAll   ja:"+ja.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据type查询
	private void LoadFindByType(HttpServletResponse response,String type){
		list = new ArrayList<Info>();
		list = dao.findByType(type);
		JSONArray ja = new JSONArray();
		for(Info i:list){
			JSONObject js = new JSONObject();
			js.put("info_id", i.getId());
			js.put("info_title", i.getInfoTitle());
			js.put("info_time", i.getInfoTime());
			js.put("info_content", i.getInfoContent());
			js.put("info_img", i.getInfoImg());
			js.put("info_type", i.getInfoType());
			ja.add(js);
		}
		try {
			PrintWriter out = response.getWriter();
			out.println(ja.toString());
			System.out.println("----------LoadFindByType   ja:"+ja.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//添加
	private void LoadInsertInfo(HttpServletResponse response,Info info){
		boolean flag = dao.insertInfo(info);
		JSONObject js = new JSONObject();
		js.put("isSuc", js.toString());
		try {
			PrintWriter pw = response.getWriter();
			pw.println(js.toString());
			System.out.println("-------------------------添加成功---------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------------------添加失败---------------");
		}
	}
}
