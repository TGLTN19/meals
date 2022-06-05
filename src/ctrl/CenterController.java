package ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DiningCarService;
import model.FoodService;
import model.FoodTypeService;
import model.UserService;

/**
 * Servlet implementation class CenterController
 */
@WebServlet("*.action")
public class CenterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CenterController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//创建session对象保存信息
		HttpSession session = request.getSession();
		//得到相对项目的url，不带项目名称  
		String path = request.getServletPath();
		path = path.substring(path.lastIndexOf('/') + 1, path.indexOf(".action"));
		
		if (path.equals("homepage")) {
			// 首页
			System.out.println("加载主页");
			FoodService food = new FoodService();
			request.setAttribute("hot", food.getHotFoods());
			request.setAttribute("special", food.getSpecialFoods());
			request.setAttribute("recomm", food.getRecommFoods());
			request.getRequestDispatcher("/pages/homepage.jsp").forward(request, response);
			//登录
		}else if (path.equals("login")) {
			// 用户登录
						// 读取请求参数un和pw
						String un = request.getParameter("un");
						String pw = request.getParameter("pw");
						// 判断用户名和密码是否正确
						if (un == null || pw == null || un.equals("") || pw.equals("")) {
							// 用户未登录
							request.setAttribute("msg", "请先登录！");
							request.setAttribute("href", request.getContextPath() + "/homepage.action");
							request.getRequestDispatcher("/pages/result.jsp").forward(request, response);
						} else {
							// 调用模型进行身份验证
							UserService us = new UserService();
							Map<String, String> r = us.login(un, pw);
							if (r != null) {
								// 登录成功
								String ident = r.get("ident");
								// 用session保存用户的登录信息
								session.setAttribute("loginID", r.get("id"));
								session.setAttribute("loginName", un);
								session.setAttribute("ident", ident);
								if (ident.equals("1")) {
									// 管理员
									response.sendRedirect(request.getContextPath() + "/admin/admin_index.action");
								} else {
									// 普通用户
									response.sendRedirect(request.getContextPath() + "/user/user_index.action");
								}
							} else {
								// 登录失败
								// 不合法用户
								request.setAttribute("msg", "用户名或密码错误！");
								request.setAttribute("href", request.getContextPath() + "/homepage.action");
								request.getRequestDispatcher("/pages/result.jsp").forward(request, response);
							}
						}
			//注册
		}else if (path.equals("register")) {
			 request.setCharacterEncoding("utf-8");
	            String un = request.getParameter("un");
	            String pw = request.getParameter("pw");
	            String tel = request.getParameter("tel");
	            String addr = request.getParameter("addr");
	            UserService us = new UserService();
	            //System.out.println(un);
	            //System.out.println(pw);
	            //System.out.println(tel);
	            //System.out.println(addr);

	            if (us.check(un)) {
	                //用户名可用
	                int r = us.register(un, pw, tel, addr);
	                System.out.println("r:" + r);
	                if (r == 1) request.setAttribute("msg", "注册成功！");
	                else request.setAttribute("msg", "注册失败！");
	            } else {
	                request.setAttribute("msg", "用户名已存在！");
	            }
	            request.setAttribute("href", request.getContextPath() + "/homepage.action");
	            request.getRequestDispatcher("/pages/result.jsp").forward(request, response);

		}else if (path.equals("show_detail")) {
			// 菜品详细信息
			FoodService food = new FoodService();
			String id = request.getParameter("id");
			request.setAttribute("food", food.getFood(id));
			request.getRequestDispatcher("/pages/show_detail.jsp").forward(request, response);
		}
		//添加购物车
		else if (path.equals("user_add_dc")) {
			String[] ids = request.getParameterValues("ids");
			String userid = (String) session.getAttribute("loginID");
			DiningCarService dc = new DiningCarService();
			int r = dc.addToDC(userid, ids);
			request.setAttribute("msg", "成功将" + r + "个菜品加入点餐车！");
			request.setAttribute("href", request.getContextPath() + "/user/user_show_dc.action");
			request.getRequestDispatcher("/pages/result.jsp").forward(request, response);
		//查看购物车
		}else if (path.equals("user_show_dc")) {
			String userid = (String) session.getAttribute("loginID");
			DiningCarService dc = new DiningCarService();
			request.setAttribute("dc", dc.showDC(userid));
			request.getRequestDispatcher("/pages/user/user_show_dc.jsp").forward(request, response);
		//删除购物车
		}else if (path.equals("user_del_dc")) {
			String[] ids = request.getParameterValues("ids");
			DiningCarService dc = new DiningCarService();
			int r = dc.delFromDC(ids);
			request.setAttribute("msg", "成功将" + r + "个菜品从点餐车删除！");
			request.setAttribute("href", request.getContextPath() + "/user/user_show_dc.action");
			request.getRequestDispatcher("/pages/result.jsp").forward(request, response);
		//实现登出效果
		}else if (path.equals("logout")) {
			session.removeAttribute("loginID");
            session.removeAttribute("loginName");
            session.removeAttribute("ident");
            session.invalidate();
            //重定向到主页
            response.sendRedirect(request.getContextPath() + "/homepage.action");
		}
		else if (path.equals("user_index")) {
			FoodService f = new FoodService();
			String s_fn = request.getParameter("s_fn");
			String s_type = request.getParameter("s_type");
			request.setAttribute("foods", f.getFoods(s_fn, s_type));
			FoodTypeService ft = new FoodTypeService();
			request.setAttribute("types", ft.getAllTypes());
			request.getRequestDispatcher("/pages/user/user_index.jsp").forward(request, response);	
		}
		else if (path.equals("user_edit")) {
			response.setContentType("text/html");
	        request.setCharacterEncoding("utf-8");
	        String id = request.getParameter("id");
	        String un = request.getParameter("un");
	        String pw = request.getParameter("pw");
	        String addr = request.getParameter("addr");
	        String tel = request.getParameter("tel");
	        Map<String, String> m = new HashMap<String, String>();
	       // System.out.println(m);
	        m.put("id", id);
	        //System.out.println(m.put("id", id));
	        m.put("username", un);
	        m.put("password", pw);
	        m.put("telephone", tel);
	        m.put("address", addr);

	        int r = new UserService().updateUser(m);
	        System.out.println("影响行数:"+r);
	        System.out.println("用户名："+un);
	        if (r == 1) {
	        	System.out.println("修改成功！！！");
	        	request.setAttribute("msg", "修改成功！！！");
	        }
	        
	        else {
	        	System.out.println("修改失败！！！");
	        	request.setAttribute("msg", "修改失败！！！");
	        }
	      //重定向到主页
	        //response.sendRedirect(request.getContextPath() + "/homepage.action");	
	        //request.getRequestDispatcher("/pages/result.jsp").forward(request, response);
	        request.setAttribute("href", request.getContextPath() + "/user/user_index.action");
            request.getRequestDispatcher("/pages/result.jsp").forward(request, response);
		}
	}
	

}
