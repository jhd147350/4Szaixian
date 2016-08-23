package com.fenghuo.jhdwxt.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fenghuo.jhdwxt.service.dao.RecardDao;
import com.fenghuo.jhdwxt.service.entity.Recard;

public class RecordServlet extends HttpServlet {

	RecardDao dao = RecardDao.getInstance();
	List<Recard> list;
	String coding = "utf-8";

	public RecordServlet() {
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
		String parameter = request.getParameter("params");
		String str_action = request.getParameter("action");
		int action = Integer.parseInt(str_action);
		System.out.println("parameter :"+parameter);
		System.out.println("action  " + action);
		 switch (action) {
		 case 1:// 表示按uid查找
		 {
		 String uid = request.getParameter("uid");
		 loadRecardFindByUid(response, uid);

		 }
		
		 break;
		 case 2:// 表示按uid和月份查找
		 {
		 String ui = request.getParameter("uid");
		 String s = request.getParameter("month");
		 String uid = new String(ui.getBytes("ISO-8859-1"), coding);
		 String month = new String(s.getBytes("ISO-8859-1"), coding);
		 System.out.println("uid " + uid);
		 System.out.println("month " + month);
		
		 loadRecarfdByTime(response, uid, month);
		
		 }
		
		 break;
		 case 3:// 表示添加
		 {
		 String uid = request.getParameter("uid");
		 String xcType = request.getParameter("xctype");
		 String xcMoney = request.getParameter("xcmoney");
		 String xcTimes = request.getParameter("xctime");
		 String xcMonth = request.getParameter("xcmonth");
		 String xcYear = request.getParameter("xcyear");
		 double money = Double.parseDouble(xcMoney);
		 System.out.println("xcYear--->"+xcYear);
		 String type = new String(xcType.getBytes("ISO-8859-1"), coding);
		 Recard re = new Recard();
		 re.setUid(uid);
		 re.setXcMoney(money);
		 re.setXcTime(xcTimes);
		 re.setXcType(type);
		 re.setXcMonth(xcMonth);
		 re.setXcYear(xcYear);
		 System.out.println(re.toString());
		 System.out.println("----------------添加------------");
		 insertRecard(response, re);
		
		 }
		 break;
		 case 4:// 表示按uid和年份查找
		 {
		 JSONObject object = JSONObject.fromObject(parameter);
		 String ui = request.getParameter("uid");
		 String s = request.getParameter("year");
		 String uid = new String(ui.getBytes("ISO-8859-1"), coding);
		 String year = new String(s.getBytes("ISO-8859-1"), coding);
		 System.out.println("uid " + uid);
		 System.out.println("year " + year);
		 loadRecardFindByYear(response, uid, year);
		 }
		 break;
		 case 5:// 表示按uid和月份和类型查找
		 {
		 String ui = request.getParameter("uid");
		 String s = request.getParameter("month");
		 String ss = request.getParameter("type");
		 String uid = new String(ui.getBytes("ISO-8859-1"), coding);
		 String month = new String(s.getBytes("ISO-8859-1"), coding);
		 String type = new String(ss.getBytes("ISO-8859-1"), coding);
		 System.out.println("uid " + uid);
		 System.out.println("month " + month);
		 System.out.println("type " + type);
		 loadRecardFindByMonthType(response, uid, month, type);
		 }
		 break;
		 case 6:// 表示按uid和年份和类型查找
		 {
		 String ui = request.getParameter("uid");
		 String s = request.getParameter("year");
		 String ss = request.getParameter("type");
		 String uid = new String(ui.getBytes("ISO-8859-1"), coding);
		 String year = new String(s.getBytes("ISO-8859-1"), coding);
		 String type = new String(ss.getBytes("ISO-8859-1"), coding);
		 System.out.println("uid " + uid);
		 System.out.println("year " + year);
		 System.out.println("type " + type);
		 loadRecardFindByYearType(response, uid, year, type);
		 }
		
		 break;
		 default:
		 break;
		 }

	}

	public void init() throws ServletException {
		// Put your code here
	}

	private void loadRecardFindByUid(HttpServletResponse response, String uid) {

		list = new ArrayList<Recard>();
		JSONArray ja = new JSONArray();
		list = dao.findByUid(uid);
		for (Recard r : list) {
			JSONObject js = new JSONObject();
			js.put("recard_id", r.getId());
			js.put("recard_uid", r.getUid());
			js.put("recard_xctype", r.getXcType());
			js.put("recard_xcmoney", r.getXcMoney());
			js.put("recard_xctime", r.getXcTime());
			js.put("recard_xcmonth", r.getXcMonth());
			js.put("recard_xcyear", r.getXcYear());
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

	private void loadRecarfdByTime(HttpServletResponse response, String uid,
			String date) {
		list = new ArrayList<Recard>();
		list = dao.findByDate(uid, date);
		{
			JSONArray ja = new JSONArray();
			for (Recard r : list) {
				JSONObject js = new JSONObject();
				js.put("recard_id", r.getId());
				js.put("recard_uid", r.getUid());
				js.put("recard_xctype", r.getXcType());
				js.put("recard_xcmoney", r.getXcMoney());
				js.put("recard_xctime", r.getXcTime());
				js.put("recard_xcmonth", r.getXcMonth());
				js.put("recard_xcyear", r.getXcYear());
				System.out.println(r.getXcYear());
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
	}

	private void insertRecard(HttpServletResponse response, Recard recard) {
		// TODO Auto-generated method stub

		JSONObject jo = new JSONObject();
		try {

			boolean isSuc = dao.insertRecard(recard);
			jo.put("isSuc", isSuc);
			System.out.println("-------------------------添加" + isSuc
					+ "---------------");
			PrintWriter pw = response.getWriter();
			pw.println(jo.toString());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------------------添加异常---------------");
		}
	}

	private void loadRecardFindByYear(HttpServletResponse response, String uid,
			String year) {

		list = new ArrayList<Recard>();
		JSONArray ja = new JSONArray();
		list = dao.findByYear(uid, year);
		for (Recard r : list) {
			JSONObject js = new JSONObject();
			js.put("recard_id", r.getId());
			js.put("recard_uid", r.getUid());
			js.put("recard_xctype", r.getXcType());
			js.put("recard_xcmoney", r.getXcMoney());
			js.put("recard_xctime", r.getXcTime());
			js.put("recard_xcmonth", r.getXcMonth());
			js.put("recard_xcyear", r.getXcYear());
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

	private void loadRecardFindByYearType(HttpServletResponse response,
			String uid, String year, String type) {

		list = new ArrayList<Recard>();
		JSONArray ja = new JSONArray();
		list = dao.findByYearAndType(uid, year, type);
		for (Recard r : list) {
			JSONObject js = new JSONObject();
			js.put("recard_id", r.getId());
			js.put("recard_uid", r.getUid());
			js.put("recard_xctype", r.getXcType());
			js.put("recard_xcmoney", r.getXcMoney());
			js.put("recard_xctime", r.getXcTime());
			js.put("recard_xcmonth", r.getXcMonth());
			js.put("recard_xcyear", r.getXcYear());
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

	private void loadRecardFindByMonthType(HttpServletResponse response,
			String uid, String month, String type) {

		list = new ArrayList<Recard>();
		JSONArray ja = new JSONArray();
		list = dao.findByMonthAndType(uid, month, type);
		for (Recard r : list) {
			JSONObject js = new JSONObject();
			js.put("recard_id", r.getId());
			js.put("recard_uid", r.getUid());
			js.put("recard_xctype", r.getXcType());
			js.put("recard_xcmoney", r.getXcMoney());
			js.put("recard_xctime", r.getXcTime());
			js.put("recard_xcmonth", r.getXcMonth());
			js.put("recard_xcyear", r.getXcYear());
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
}
