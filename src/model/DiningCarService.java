package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class DiningCarService {
private DBUtil db=null;
	
	public DiningCarService() {
		db=new DBUtil();
	}
	
	public List<Map<String, String>> showDC(String userid){
		String sql =  "select f.*,ft.typename,dc.id as dcid from food f join foodtype ft on f.type = ft.id join diningcar dc on f.id = dc.foodid where dc.userid = ? order by dcid desc";
	String[] params= {userid};
	return db.getList(sql, params);
	}
	
	
	public int addToDC(String userid, String[] ids) {
		int r=0;
		if (ids!=null) {
			String sql = "insert into diningcar values(null,?,?)";
			for (String foodid : ids) {
				r+=db.update(sql, new String[] {userid,foodid});
			}
			sql="update food set hits=hits+1 where id=?";
			for (String id : ids) {
				db.update(sql, new String[] {id});
			}
		}
		return r;
	}

	//从点餐车删除
		public int delFromDC(String[] ids){
			int r =0;
			if(ids != null){
				String sql="update food set hits = hits-1 where id =(select foodid from diningcar where id=?)";
				for(String dcid:ids){
					//更新点餐率
					db.update(sql, new String[]{dcid});
				}
				sql="delete from diningcar where id=?";
				for(String id:ids){
					//从点餐车删除
					r+=db.update(sql, new String[]{id});
				}
			}
			return r;
		}
		public Map<String, List<Map<String, String>>> showAllDC(){
			Map<String, List<Map<String, String>>>
			dcs=new HashMap<String, List<Map<String,String>>>();
			String sql="select distinct u.id,u.username from user u join diningcar dc on u.id=dc.userid";
			List<Map<String, String>>users=db.getList(sql);
			for (Map<String, String> m : users) {
				dcs.put(m.get("username"), showDC(m.get("id")));
				
			}
			return dcs;
			
			
		}

}
