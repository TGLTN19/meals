package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
	// 数据库驱动程序
	private String driver;
	// 数据库连接信息
	private String url;
	// 连接数据库的用户名
	private String username;
	// 连接数据库的密码
	private String password;
	// 连接对象
	private Connection con;
	// 预编译语句对象
	private PreparedStatement pstmt;
	// 查询结果分页时，每页显示记录数。
	public static final long PAGE_REC_NUM = 8;

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DBUtil() {
		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/meal?serverTimezone=UTC&useSSL=false";
		username = "root";
		password = "root";
	}

	// 初始化方法，加载驱动程序，获得数据库的连接对象。
	private void init() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 数据库操作对象的关闭
	private void close() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 为预编译的SQL语句中的占位符?设置值，值被放在字符串数组params中。
	private void setParams(String[] params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				try {
					pstmt.setString(i + 1, params[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}
	}

	// 执行更新类（增删改）的SQL语句，SQL语句中含有占位符。
	public int update(String sql, String[] params) {
		int result = 0;
		init();
		try {
			pstmt = con.prepareStatement(sql);
			setParams(params);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 执行更新类（增删改）的SQL语句，SQL语句中不含有占位符。
	public int update(String sql) {
		return update(sql, null);
	}

	// 执行返回多条记录的查询操作，结果被封装到List中。
	public List<Map<String, String>> getList(String sql, String[] params) {
		List<Map<String, String>> list = null;
		init();
		try {
			pstmt = con.prepareStatement(sql);
			setParams(params);
			ResultSet rs = pstmt.executeQuery();
			list = getListFromRS(rs);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<Map<String, String>> getList(String sql) {
		return getList(sql, null);
	}

	// 执行返回至多一条记录的查询操作，结果被封装到Map中。
	public Map<String, String> getMap(String sql, String[] params) {
		Map<String, String> m = null;
		List<Map<String, String>> l = getList(sql, params);
		if (l != null && l.size() != 0) {
			m = (Map<String, String>) (l.get(0));
		}
		return m;
	}

	public Map<String, String> getMap(String sql) {
		return getMap(sql, null);
	}

	// 将结果集中的内容封装到List中
	private List<Map<String, String>> getListFromRS(ResultSet rs) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			Map<String, String> m = new HashMap<String, String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String colName = rsmd.getColumnLabel(i);
				String s = rs.getString(colName);
				if (s != null) {
					m.put(colName, s);
				}
			}
			list.add(m);
		}
		return list;
	}

	// 查询结果分页时，返回分页信息的Map，包括总页数，每页记录数和当前页中的记录。
	public Map<String, Object> getPage(String sql, String[] params, String curPage) {
		Map<String, Object> page = new HashMap<String, Object>();
		String newSql = sql + " limit " + (Long.parseLong(curPage) - 1) * PAGE_REC_NUM + "," + PAGE_REC_NUM;
		List<Map<String, String>> pageList = getList(newSql, params);
		sql = sql.toLowerCase();
		String countSql = "";
		if (sql.indexOf("group") >= 0) {
			countSql = "select count(*) as tempNum from (" + sql + ") as temp";
		} else {
			countSql = "select count(*) as tempNum " + sql.substring(sql.indexOf("from"));
		}
		String count_s = (String) getMap(countSql, params).get("tempNum");
		long count = Long.parseLong(count_s);
		long totalPage = 0;
		if (count % PAGE_REC_NUM == 0)
			totalPage = count / PAGE_REC_NUM;
		else
			totalPage = count / PAGE_REC_NUM + 1;
		page.put("list", pageList);
		page.put("totalPage", totalPage);
		page.put("recNum", PAGE_REC_NUM);
		return page;
	}

	public Map<String, Object> getPage(String sql, String curPage) {
		return getPage(sql, null, curPage);
	}
}
