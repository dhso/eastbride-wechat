package modules.system.model;

import com.jfinal.plugin.activerecord.Model;

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
	 * 获取用户
	 * 
	 * @param username
	 * @return
	 */
	public ShiroModel getRole(String username) {
		ShiroModel user = getUser(username);
		ShiroModel result = null;
		if (null != user) {
			Long id = user.getLong("id");
			result = ShiroModel.dao.findFirst("select * from roles where id = (select role_id from shiro_user_roles where user_id = ?)", id);
		}
		return result;
	}

	/**
	 * 获取链接
	 * 
	 * @param username
	 * @param cascade_id
	 */
	public void getMenus(String username, String cascade_id) {
		ShiroModel user = getUser(username);
		Long id = user.getLong("id");
		ShiroModel.dao.find("select * from ", user);
	}

}
