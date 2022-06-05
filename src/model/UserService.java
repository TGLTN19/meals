package model;

import java.util.List;
import java.util.Map;
import java.util.*;
import util.DBUtil;
public class UserService {

    private DBUtil db = null;
    //连接数据库
    public UserService() {
        db=new DBUtil();
    }
    
    /**
     * 根据id查找用户
     *
     * @param id
     * @return 封装了一个用户信息的map对象
     */
    public Map<String, String> login(String un,String pw) {
    	String sql = "SELECT * FROM `user` WHERE `user`.username = ? AND `user`.`password` = ?";
    	String[] params = {un,pw};
    	return db.getMap(sql,params);
    	
    }
    
    public int updateUser(String pw,String tel,String addr,String id){
        String sql = "update user set password=?,telephone=?,address=? where id=?";
        String[] params = {pw,tel,addr,id};
        return db.update(sql,params);
    }
    
    public int updateUser(Map<String,String> m){
        String sql = "update user set username=? ,password=?,telephone=?,address=? where id=?";
        String[] params = {m.get("username"),m.get("password"),m.get("telephone"),m.get("address"),m.get("id")};
        return db.update(sql,params);
    }

    /**
     * 根据id查找用户
     * @param id
     * @return 封装了一个用户信息的map对象
     */
    public Map<String,String> getUserById(String id){
        String sql="select * from user where id= ? ;";
        String[] params = {id};
        return db.getMap(sql,params);

    }
    //根据用户名模糊查找所以用户;
    public List<Map<String, String>> getUsers(String username){
        String sql = "select * from user";
        String[] params = null;
        if(username!=null&&!"".equals(username)) {
            sql=sql+" where username like ?";
            params = new String[] {
                    "%"+username+"%"
            };

        }
        return db.getList(sql, params);

    }
    //删除用户
    public int delUser(int id){
        String sql="delete from user where id="+id;
        return db.update(sql);
    }

    /**
     * 判断用户名是否可用
     *
     * @param username
     * @return true表示可用，false表示用户名已被使用
     */
	public boolean check(String username) {
		 boolean f = false;
	        String sql = "select * from user where username=?";
	        Map<String, String> u = db.getMap(sql, new String[]{username});
	        if (u == null) {
	            f = true;
	            System.out.println("可以注册");
	        }
	        System.out.println("check检查了");
	        return f;
	}
	/**
     * 注册用户
     *
     * @param un
     * @param pw
     * @param tel
     * @param addr
     * @return int 影响的行数
     */
	public int register(String un, String pw, String tel, String addr) {
        String sql = "insert into user values(null,?,?,0,?,?)";
        String[] params = {un, pw, tel, addr};
        System.out.println("经过注册了");
        return db.update(sql, params);
    }
}
