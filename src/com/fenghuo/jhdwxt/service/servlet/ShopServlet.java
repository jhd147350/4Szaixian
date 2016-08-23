package com.fenghuo.jhdwxt.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fenghuo.jhdwxt.service.dao.ShopsDao;
import com.fenghuo.jhdwxt.service.entity.Shops;

public class ShopServlet extends HttpServlet {

	String coding = "utf-8";
	ShopsDao dao = ShopsDao.getInstance();
	List<Shops> list ;
	public ShopServlet() {
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
		request.setCharacterEncoding(coding);
		response.setCharacterEncoding(coding);
		String actionStr = request.getParameter("action");
		int action = Integer.parseInt(actionStr);
		System.out.println("--------ShopServlet action:"+action);
		switch (action) {
		case 1:{
			String brandStr = request.getParameter("brand");
			String seriesStr = request.getParameter("series");
			String modelStr = request.getParameter("model");
            String typeStr = request.getParameter("type");
            String brand = new String(brandStr.getBytes("ISO-8859-1"),coding);
            String series = new String(seriesStr.getBytes("ISO-8859-1"),coding);
            String model = new String(modelStr.getBytes("ISO-8859-1"),coding);
            String type = new String(typeStr.getBytes("ISO-8859-1"),coding);
            System.out.println("brand series model type"+brand+series+model+type);
            try {
				LoadFindShop(response, brand, series, model, type);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		}
			
			break;

		default:
			break;
		}
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

	private void LoadFindShop(HttpServletResponse response,String brand,
			String series,String model,String type) throws SQLException{
		list = new ArrayList<Shops>();
		list = dao.findShops(brand, series, model, type);
		JSONArray ja = new JSONArray();
		for(Shops s:list){
			JSONObject js = new JSONObject();
			js.put("shop_id", s.getId());
			js.put("shop_brand", s.getBrand());
			js.put("shop_series", s.getSeries());
			js.put("shop_model", s.getModel());
			js.put("shop_type", s.getType());
			js.put("shop_name", s.getShopName());
			js.put("shop_yuyue", s.getShopYuyue());
			js.put("shop_4s", s.getShop4S());
			js.put("shop_distance", s.getShopDistance());
			js.put("shop_address", s.getShopAddress());
			js.put("shop_img", s.getShopImg());
			ja.add(js);
			
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(ja.toString());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
