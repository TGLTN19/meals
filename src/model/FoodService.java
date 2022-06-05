package model;

import java.util.List;
import java.util.Map;

import util.DBUtil;

public class FoodService {
private DBUtil db=null;
//	连接数据库
	public FoodService() {
		db=new DBUtil();
	}
	
//	 public static void main(String[] args) {
//			FoodService fs = new FoodService();
//			Map<String, String> m = fs.getFood("id");
//		}
	 
	/**
	 * 获取热门菜
	 * @return
	 */
	public List<Map<String, String>> getHotFoods(){
		String sql ="SELECT food.id,food.foodname,food.price,food.picture FROM food ORDER BY food.hits Desc LIMIT 0, 3";  
				return db.getList(sql);
	}
	/**
	 * 获取特价菜
	 * @return
	 */
	public List<Map<String, String>> getSpecialFoods(){
		String sql = "SELECT food.id,food.foodname,food.picture, food.`comment` FROM food WHERE food.`comment` > 0 ORDER BY food.`comment` DESC LIMIT 0, 3";
				return db.getList(sql);
	}
	/**
	 * 获取厨师推荐
	 * @return
	 */
	public List<Map<String, String>> getRecommFoods(){
		String sql = "SELECT food.id,food.foodname,food.price,food.picture,food.`comment` FROM food WHERE food.`comment` = 0 ORDER BY food.price DESC LIMIT 0, 3";
				return db.getList(sql);
	}
	 public Map<String,String> getFood(String id){
	      String sql="SELECT f.*, ft.typename FROM food f JOIN foodtype ft ON f.type = ft.id WHERE f.id = ?";
		 String[] params = {id};
	        return db.getMap(sql,params);
	    }
	 public List<Map<String,String>> getFoods(String s_fn,String s_type){
			String sql = "select f.*,ft.typename from food f join foodtype ft on f.type = ft.id where 1=1";
			String[] params = null;
			if(s_fn !=null && s_type !=null){
				sql = sql + " and foodname like ? and type like ?";
				params = new String[]{"%"+s_fn+"%","%"+s_type+"%"};
			}else if(s_fn == null && s_type !=null){
				sql = sql + " and type like ?";
				params = new String[]{"%"+s_type+"%"};
			}else if(s_fn !=null && s_type == null){
				sql = sql + " and foodname like ?";
				params = new String[]{"%"+s_fn+"%"};
			}
			sql = sql + " order by hits desc, type asc, f.id asc";
			return db.getList(sql, params);
		} 
}
