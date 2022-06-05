package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 Connection conn=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String un = request.getParameter("un");
        String pw = request.getParameter("pw");
        //判空
        if (un != null && pw != null && !"".equals(un) && !"".equals(pw)) {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
//            定义数据库的连接地址
                String url = "jdbc:mysql://localhost:3306/meal?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
                conn = DriverManager.getConnection(url, "root", "root");
                String sql = "select * from user where username = '" + un + "' and password = '" + pw + "'";
//                String sql = "select * from user";
                pstmt = conn.prepareStatement(sql);
                rs =  pstmt.executeQuery();
                while (rs.next()) {
                    //登录成功, 讲登录用户信息保存到session中
                	//获取ident
                    String ident = rs.getString("ident");
                    //保存id
                    session.setAttribute("loginID", rs.getInt(1));
                    //保存username
                    session.setAttribute("loginName", rs.getString("username"));
                    session.setAttribute("ident", ident);
                    if (ident.equals("1")) out.print("管理员登录成功");
                    else out.print("普通用户登录成功");
//                    out.print(rs.getString("password"));
                }



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //关闭连接
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
		
		
}
