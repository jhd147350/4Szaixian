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

import com.fenghuo.jhdwxt.service.dao.DistancesDao;
import com.fenghuo.jhdwxt.service.entity.Distances;


public class DistancesServlet extends HttpServlet {

	DistancesDao dao ;
	private String coding = "utf-8";
	public DistancesServlet() {
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
		
		String str_action = request.getParameter("action");
		int action = Integer.parseInt(str_action);
		
		System.out.println("action  " + action);
		switch (action) {
		case 1:{
			String uid = request.getParameter("uid");
			LoadFindByUid(response, uid);
		}
			
			break;
		case 2:{
			String uid = request.getParameter("uid");
			String typeStr = request.getParameter("type");
			String disStr = request.getParameter("distancess");
			String type = new String(typeStr.getBytes("ISO-8859-1"),coding);
			double distancess = Double.parseDouble(disStr);
			System.out.println("uid :"+uid);
			System.out.println("type:"+type);
			System.out.println("distancess :"+distancess);
			Distances dis = new Distances();
			dis.setUid(uid);
			dis.setType(type);
			dis.setDistancess(distancess);
			insertDistance(response, dis);
		}

		default:
			break;
		}
	}
	
	private void LoadFindByUid(HttpServletResponse response,String uid){
		List<Distances> list = new ArrayList<Distances>();
		dao = DistancesDao.getInstance();
		list = dao.findByUid(uid);
		JSONArray ja = new JSONArray();
		for(Distances d:list){
			JSONObject js = new JSONObject();
			js.put("distance_id", d.getId());
			js.put("distance_uid", d.getUid());
			js.put("distance_type", d.getType());
			js.put("distance_dis", d.getDistancess());
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
		}
		
	}
	private void insertDistance(HttpServletResponse response, Distances dis) {
		// TODO Auto-generated method stub
       dao = DistancesDao.getInstance();
		JSONObject jo = new JSONObject();
		try {

			boolean isSuc = dao.insertDistance(dis);
			jo.put("isSuc", isSuc);
			System.out.println("-------------------------ÃÌº”" + isSuc
					+ "---------------");
			PrintWriter pw = response.getWriter();
			pw.println(jo.toString());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------------------ÃÌº”“Ï≥£---------------");
		}
	}
}
