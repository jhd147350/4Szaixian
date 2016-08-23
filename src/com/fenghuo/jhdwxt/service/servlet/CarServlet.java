package com.fenghuo.jhdwxt.service.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fenghuo.jhdwxt.service.dao.BrandDao;
import com.fenghuo.jhdwxt.service.dao.CarDao;
import com.fenghuo.jhdwxt.service.dao.ModelDao;
import com.fenghuo.jhdwxt.service.dao.SeriesDao;
import com.fenghuo.jhdwxt.service.dao.SubsDao;
import com.fenghuo.jhdwxt.service.entity.Brand;
import com.fenghuo.jhdwxt.service.entity.Car;
import com.fenghuo.jhdwxt.service.entity.Model;
import com.fenghuo.jhdwxt.service.entity.Series;
import com.fenghuo.jhdwxt.service.entity.Subs;

public class CarServlet extends HttpServlet {

	CarDao carDao = CarDao.getInstance();
	List<Car> carlist;
	
	public CarServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	

		//生成图片存储的路径
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "image";
		File file = new File(path);
		if (!file.exists()) {
			System.out.println(path);
			file.mkdirs();
		}


		System.out.println("---------------------CarServlet:doGet---------------");
		String str_action =request.getParameter("action");
		int action=Integer.parseInt(str_action);
		switch (action) {
		case 1://加载品牌列表
			System.out.println("---------------------CarServlet:doGet---请求brand");
			loadBrand(response);

			break;
		case 2:

			String str_brand_id=request.getParameter("brand_id");
			long brand_id=Long.parseLong(str_brand_id);
			System.out.println("---------------------CarServlet:doGet---请求series，brand_id是"+brand_id);
			loadSeries(response, brand_id);

			break;
		case 3:
			String str_series_id=request.getParameter("series_id");
			long series_id=Long.parseLong(str_series_id);
			System.out.println("---------------------CarServlet:doGet---请求subs，series_id是"+series_id);
			loadSubs(response,series_id);
			break;
		case 4:
			String str_subs_id=request.getParameter("subs_id");
			long subs_id=Long.parseLong(str_subs_id);
			System.out.println("---------------------CarServlet:doGet---请求model，subs_id是"+subs_id);
			loadModel(response,subs_id);
			break;
		case 5:
			String str_model_id=request.getParameter("model_id");
			long model_id=Long.parseLong(str_model_id);
			System.out.println("---------------------CarServlet:doGet---请求mcar，model_id是"+model_id);
			loadCar(response,model_id);
		case 6:{
			String uid = request.getParameter("uid");
			System.out.println("-------------getCar ByUid----------");
			LoadCarByUid(response, uid);
		}
			
			break;
		case 7:{
			String uid = request.getParameter("uid");
			String brandStr = request.getParameter("brand");
			String seriesStr = request.getParameter("series");
			String modelStr = request.getParameter("model");
			String img = request.getParameter("img");
			String brand = new String(brandStr.getBytes("ISO-8859-1"),"UTF-8");
			String series = new String(seriesStr.getBytes("ISO-8859-1"),"UTF-8");
			String model = new String(modelStr.getBytes("ISO-8859-1"),"UTF-8");
			Car c = new Car();
			c.setUid(uid);
			c.setBrand(brand);
			c.setSeries(series);
			c.setModel(model);
			c.setImg(img);
			System.out.println("-------------insert car-----------");
			LoadInsertCar(response, c );
		}
		break;
		case 8:{
			String uid = request.getParameter("uid");
			String brandStr = request.getParameter("brand");
			String seriesStr = request.getParameter("series");
			String modelStr = request.getParameter("model");
			String img = request.getParameter("img");
			String brand = new String(brandStr.getBytes("ISO-8859-1"),"UTF-8");
			String series = new String(seriesStr.getBytes("ISO-8859-1"),"UTF-8");
			String model = new String(modelStr.getBytes("ISO-8859-1"),"UTF-8");
			
			System.out.println("-------------update car-----------"+brandStr+seriesStr+modelStr);
			LoadUpdateCar(response, uid, brand, series, model, img);
		}
		break;

		default:
			break;
		}

	}
	

	private void loadCar(HttpServletResponse response, long model_id) {
		// TODO Auto-generated method stub
		ModelDao md=ModelDao.getInstance();
		Model m=md.searchCar(model_id);
		JSONObject temp=new JSONObject();
		temp.put("model_id", m.getId());
		temp.put("modle_name", m.getName());
		temp.put("model_img", m.getImg());
		temp.put("model_sortkey", m.getSortkey());
		temp.put("subs_id", m.getSubs_id());
		try {
			PrintWriter pw = response.getWriter();
			pw.println(temp.toString());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("CarServlet-loadCar--异常");
		}		
	}

	private void loadModel(HttpServletResponse response, long subs_id) {
		ModelDao md=ModelDao.getInstance();
		List<Model> list=md.searchModel(subs_id);
		JSONArray ja=new JSONArray();
		for (Model m : list) {
			JSONObject temp=new JSONObject();
			temp.put("model_id", m.getId());
			temp.put("model_name", m.getName());
			temp.put("model_img", m.getImg());
			temp.put("model_sortkey", m.getSortkey());
			temp.put("subs_id", m.getSubs_id());
			ja.add(temp);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("CarServlet-loadModel--异常");
		}		

	}

	private void loadSubs(HttpServletResponse response, long series_id) {
		SubsDao sd=SubsDao.getInstance();
		List<Subs> list=sd.searchSubs(series_id);
		JSONArray ja=new JSONArray();
		for (Subs s : list) {
			JSONObject temp=new JSONObject();
			temp.put("subs_id", s.getId());
			temp.put("subs_name", s.getName());
			temp.put("series_id", s.getSeries_id());
			ja.add(temp);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("CarServlet-loadSubs--异常");
		}		


	}

	private void loadBrand(HttpServletResponse response) {
		BrandDao bd=BrandDao.getInstance();
		List<Brand> list = bd.searchAll();
		JSONArray ja=new JSONArray();
		for (Brand brand : list) {
			JSONObject temp=new JSONObject();
			temp.put("brand_id", brand.getId());
			temp.put("brand_name", brand.getName());
			temp.put("brand_img", brand.getImg());
			temp.put("brand_sortkey", brand.getSortkey());
			ja.add(temp);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("CarServlet-loadBrand--异常");
		}		
	}

	private void loadSeries(HttpServletResponse response,long brand_id) {
		SeriesDao sd=SeriesDao.getInstance();
		List<Series> list = sd.searchSeries(brand_id);
		JSONArray ja=new JSONArray();
		for (Series s : list) {
			JSONObject temp=new JSONObject();
			temp.put("series_id", s.getId());
			temp.put("brand_id", s.getBrand_id());
			temp.put("series_name", s.getName());
			ja.add(temp);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("CarServlet-loadSeries--异常");
		}		
	}
	private void LoadCarByUid(HttpServletResponse response,String uid){
		Car c = new Car();
		  c = carDao.findByUid(uid);
		JSONObject js = new JSONObject();
		js.put("car_id", c.getId());
		js.put("car_uid", c.getUid());
		js.put("car_brand", c.getBrand());
		js.put("car_series", c.getSeries());
		js.put("car_model", c.getModel());
		js.put("car_img", c.getImg());
		try {
			PrintWriter pw = response.getWriter();
			pw.println(js.toString());
			System.out.println("---------LOADCARBYUID car:---"+c.toString());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//添加
	private void LoadInsertCar(HttpServletResponse response,Car car){
		boolean flag = carDao.insertCar(car);
		JSONObject js = new JSONObject();
		js.put("flag", flag);
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.println(js.toString());
			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//修改
		private void LoadUpdateCar(HttpServletResponse response,String uid,String brand,
				String series,String model,String img){
			boolean flag = carDao.updateCar(uid, brand, series, model, img);
			JSONObject js = new JSONObject();
			js.put("flag", flag);
			PrintWriter pw;
			try {
				pw = response.getWriter();
				pw.println(js.toString());
				
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
