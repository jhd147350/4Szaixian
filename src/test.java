import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fenghuo.jhdwxt.service.dao.BrandDao;
import com.fenghuo.jhdwxt.service.dao.ModelDao;
import com.fenghuo.jhdwxt.service.dao.SeriesDao;
import com.fenghuo.jhdwxt.service.dao.SubsDao;
import com.fenghuo.jhdwxt.service.dao.UserDao;
import com.fenghuo.jhdwxt.service.entity.Brand;
import com.fenghuo.jhdwxt.service.entity.Model;
import com.fenghuo.jhdwxt.service.entity.Series;
import com.fenghuo.jhdwxt.service.entity.Subs;
import com.fenghuo.jhdwxt.service.entity.User;



public class test {
	public static void main(String[] args) {
		UserDao us=UserDao.getInstance();
		String num="18333606137";
		String pass="1234567890";
		User user=new User();
		user.setNickname("¼ÖºÀ¶«Ð¡ºÅ");
		user.setPhonenum("111");
		user.setPassword("111");
		
		try {
			boolean login = us.Login(num, pass);
			System.out.println(login);
			boolean signup = us.Signup(user);
			System.out.println(signup);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		
		BrandDao bd=BrandDao.getInstance();
		List<Brand> list=bd.searchAll();
		for (Brand brand : list) {
			System.out.println(brand.toString());
		}
		
		SeriesDao sd=SeriesDao.getInstance();
		List<Series> list1 = sd.searchSeries(1);
		for (Series series : list1) {
			System.out.println(series.toString());
		}
		
		SubsDao ssd=SubsDao.getInstance();
		List<Subs> list2=ssd.searchSubs(2);
		for (Subs stubs : list2) {
			System.out.println(stubs.toString());
		}
		List<Subs> list3=ssd.searchSubs(3);
		for (Subs stubs : list3) {
			System.out.println(stubs.toString());
		}
		
		ModelDao md=ModelDao.getInstance();
		List<Model> list4 = md.searchModel(2);
		for (Model model : list4) {
			System.out.println(model.toString());
		}
	}

}
