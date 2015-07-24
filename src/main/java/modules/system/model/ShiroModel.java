package modules.system.model;

import java.util.List;

import org.apache.shiro.config.Ini;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

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
	 * 获取所有链接
	 * 
	 * @return
	 */
	public Page<Record> getAllUrls(Integer pageNumber,Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from shiro_urls su left join shiro_urls_type sut on sut.url_type_id = su.url_type_id where 1=1 order by su.url_order desc");
	}

}
