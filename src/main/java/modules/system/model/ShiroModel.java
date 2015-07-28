package modules.system.model;

import java.util.List;

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
}
