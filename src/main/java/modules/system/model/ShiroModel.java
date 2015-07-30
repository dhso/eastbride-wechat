package modules.system.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import frame.kit.RecordKit;
import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "shiro_users", pkName = "id")
public class ShiroModel extends Model<ShiroModel> {
	public static final ShiroModel dao = new ShiroModel();

	/**
	 * 获取用户
	 * 
	 * @param username
	 * @return
	 */
	public ShiroModel getUser(String username) {
		return ShiroModel.dao.findFirst("select * from shiro_users where username = ?", username);
	}

	/**
	 * 获取角色
	 * 
	 * @param username
	 * @return
	 */
	public Record getRole(String username) {
		ShiroModel user = getUser(username);
		Record role = null;
		if (null != user) {
			Integer id = user.getInt("id");
			role = Db.findFirst("select * from shiro_roles where id = (select role_id from shiro_users_roles where user_id = ?)", id);
		}
		return role;
	}

	/**
	 * 获取所有角色信息
	 * 
	 * @return
	 */
	public List<Record> getAllRole() {
		return Db.find("select sr.*,GROUP_CONCAT(srp.permission_id) permission_ids from shiro_roles sr left join shiro_roles_permissions srp on srp.role_id = sr.id GROUP BY sr.id");
	}

	/**
	 * 分页获取所有权限信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getAllRolePage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select sr.*,GROUP_CONCAT(srp.permission_id) permission_ids", "from shiro_roles sr left join shiro_roles_permissions srp on srp.role_id = sr.id GROUP BY sr.id");
	}

	/**
	 * 批量添加Role
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void insertRole(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("insert into shiro_roles(role,role_desc) values (?,?)", "role,role_desc", recordList, recordList.size());
		List<String> sqlList = new ArrayList<String>();
		Iterator<JSONObject> insertListIt = (Iterator<JSONObject>) list.iterator();
		while (insertListIt.hasNext()) {
			JSONObject itNext = insertListIt.next();
			String permission_ids = itNext.getString("permission_ids");
			if (null != permission_ids && !permission_ids.equals("")) {
				String[] permissionIds = permission_ids.split(",");
				for (int i = 0; i < permissionIds.length; i++) {
					sqlList.add("insert into shiro_roles_permissions values ((select id from shiro_roles where role = \"" + itNext.getString("role") + "\" and role_desc = \"" + itNext.getString("role_desc") + "\")," + permissionIds[i] + ")");
				}
			}
		}
		if (sqlList.size() > 0) {
			Db.batch(sqlList, sqlList.size());
		}
	}

	/**
	 * 批量更新Role
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void updateRole(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("update shiro_roles set role = ?,role_desc = ? where id = ?", "role,role_desc,id", recordList, recordList.size());
		Db.batch("delete from shiro_roles_permissions where role_id = ?", "id", recordList, recordList.size());
		List<String> sqlList = new ArrayList<String>();
		Iterator<JSONObject> insertListIt = (Iterator<JSONObject>) list.iterator();
		while (insertListIt.hasNext()) {
			JSONObject itNext = insertListIt.next();
			String permission_ids = itNext.getString("permission_ids");
			if (null != permission_ids && !permission_ids.equals("")) {
				String[] permissionIds = permission_ids.split(",");
				for (int i = 0; i < permissionIds.length; i++) {
					sqlList.add("insert into shiro_roles_permissions values (\"" + itNext.getString("id") + "\"," + permissionIds[i] + ")");
				}
			}
		}
		if (sqlList.size() > 0) {
			Db.batch(sqlList, sqlList.size());
		}
	}

	/**
	 * 批量删除Role
	 * 
	 * @param list
	 */
	public void deleteRole(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("delete from shiro_roles where id = ?", "id", recordList, recordList.size());
		Db.batch("delete from shiro_roles_permissions where role_id = ?", "id", recordList, recordList.size());
	}

	/**
	 * 获取权限
	 * 
	 * @param username
	 * @return
	 */
	public List<Record> getPermission(String username) {
		Record role = getRole(username);
		List<Record> permissions = null;
		if (null != role) {
			Integer id = role.getInt("id");
			permissions = Db.find("select * from shiro_permissions where id in (select permission_id from shiro_roles_permissions where role_id = ?)", id);
		}
		return permissions;
	}

	/**
	 * 获取所有权限信息
	 * 
	 * @return
	 */
	public List<Record> getAllPermission() {
		return Db.find("select * from shiro_permissions");
	}

	/**
	 * 分页获取所有权限信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getAllPermissionPage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from shiro_permissions");
	}

	/**
	 * 批量添加Permission
	 * 
	 * @param list
	 * @return
	 */
	public int[] insertPermission(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("insert into shiro_permissions(permission,permission_desc) values (?,?)", "permission,permission_desc", recordList, recordList.size());
	}

	/**
	 * 批量更新Permission
	 * 
	 * @param list
	 * @return
	 */
	public int[] updatePermission(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("update shiro_permissions set permission = ?,permission_desc = ? where id = ?", "permission,permission_desc,id", recordList, recordList.size());
	}

	/**
	 * 批量删除Permission
	 * 
	 * @param list
	 */
	public void deletePermission(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("delete from shiro_permissions where id = ?", "id", recordList, recordList.size());
		Db.batch("delete from shiro_roles_permissions where permission_id = ?", "id", recordList, recordList.size());
	}

	/**
	 * 获取链接
	 * 
	 * @param username
	 * @return
	 */
	public List<Record> getUrls(String username) {
		Record role = getRole(username);
		List<Record> urls = null;
		if (null != role) {
			Integer id = role.getInt("id");
			urls = Db.find("select * from shiro_urls su left join shiro_urls_type sut on sut.url_type_id = su.url_type_id where su.permission_id in (select permission_id from shiro_roles_permissions where role_id = ?) order by su.url_order desc", id);
		}
		return urls;
	}

	/**
	 * 分页获取所有链接
	 * 
	 * @return
	 */
	public Page<Record> getAllUrlsPage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select su.*,sp.permission,sp.permission_desc,sut.url_type_name", "from shiro_urls su left join shiro_permissions sp on sp.id = su.permission_id left join shiro_urls_type sut on sut.url_type_id = su.url_type_id where 1=1 order by su.url_order desc");
	}

	/**
	 * 分页获取所有链接
	 * 
	 * @return
	 */
	public List<Record> getAllUrls() {
		return Db.find("select su.*,sp.permission,sp.permission_desc,sut.url_type_name from shiro_urls su left join shiro_permissions sp on sp.id = su.permission_id left join shiro_urls_type sut on sut.url_type_id = su.url_type_id where 1=1 order by su.url_order desc");
	}

	/**
	 * 批量添加urls
	 * 
	 * @param list
	 * @return
	 */
	public int[] insertUrls(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("insert into shiro_urls(permission_id,url_type_id,url,text,icon,url_order,is_iframe) values (?,?,?,?,?,?,?)", "permission_id,url_type_id,url,text,icon,url_order,is_iframe", recordList, recordList.size());
	}

	/**
	 * 批量更新urls
	 * 
	 * @param list
	 * @return
	 */
	public int[] updateUrls(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("update shiro_urls set permission_id = ?,url_type_id = ?,url= ?,text=?,icon=?,url_order=?,is_iframe=? where id = ?", "permission_id,url_type_id,url,text,icon,url_order,is_iframe,id", recordList, recordList.size());
	}

	/**
	 * 批量删除urls
	 * 
	 * @param list
	 * @return
	 */
	public int[] deleteUrls(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("delete from shiro_urls where id = ?", "id", recordList, recordList.size());
	}

	/**
	 * 分页获取所有链接分类
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getAllUrlsTypePage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from shiro_urls_type");
	}

	/**
	 * 获取所有链接分类
	 * 
	 * @return
	 */
	public List<Record> getAllUrlsType() {
		return Db.find("select * from shiro_urls_type");
	}

	/**
	 * 批量添加urlsType
	 * 
	 * @param list
	 * @return
	 */
	public int[] insertUrlsType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("insert into shiro_urls_type(url_type_name,url_type_icon) values (?,?)", "url_type_name,url_type_icon", recordList, recordList.size());
	}

	/**
	 * 批量更新urlsType
	 * 
	 * @param list
	 * @return
	 */
	public int[] updateUrlsType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("update shiro_urls_type set url_type_name = ?,url_type_icon= ? where url_type_id = ?", "url_type_name,url_type_icon,url_type_id", recordList, recordList.size());
	}

	/**
	 * 批量删除urlsType
	 * 
	 * @param list
	 * @return
	 */
	public int[] deleteUrlsType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("delete from shiro_urls_type where url_type_id = ?", "url_type_id", recordList, recordList.size());
	}

	/**
	 * 获取所有用户信息
	 * 
	 * @return
	 */
	public List<Record> getAllUser() {
		return Db.find("select su.*,sur.role_id from shiro_users su left join shiro_users_roles sur on  sur.user_id = su.id");
	}

	/**
	 * 分页获取所有用户信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getAllUserPage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select su.*,sur.role_id", "from shiro_users su left join shiro_users_roles sur on  sur.user_id = su.id");
	}

	/**
	 * 批量添加user
	 * 
	 * @param list
	 * @return
	 */
	public void insertUser(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("insert into shiro_users(username,password,salt,locked) values (?,?,?,?)", "username,password,salt,locked", recordList, recordList.size());
		Db.batch("insert into shiro_users_roles(user_id,role_id) values ((select id from shiro_users where username = ? and password = ? and salt = ?),?)", "username,password,salt,role_id", recordList, recordList.size());
	}

	/**
	 * 批量更新user
	 * 
	 * @param list
	 */
	public void updateUser(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("update shiro_users set username = ?,password= ?,salt=?,locked=? where id = ?", "username,password,salt,locked,id", recordList, recordList.size());
		Db.batch("update shiro_users_roles set role_id = ? where user_id = ?", "role_id,id", recordList, recordList.size());
	}

	/**
	 * 批量删除user
	 * 
	 * @param list
	 */
	public void deleteUser(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("delete from shiro_users where id = ?", "id", recordList, recordList.size());
		Db.batch("delete from shiro_users_roles where user_id = ? and role_id= ?", "id,role_id", recordList, recordList.size());
	}
}
