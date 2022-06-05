package model;

import java.util.*;

import util.DBUtil;

public class FoodTypeService {
	private DBUtil db;
	//连接数据库
	public FoodTypeService() {
		db=new DBUtil();
	}
	//读取全部菜品信息，将获取信息保存在list中
	public List<Map<String, String>> getAllTypes(){
		String sql = "select * from foodtype";
		return db.getList(sql);
	}
}
