package modules.weshop.entity;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class ShopMenu extends Model<ShopMenu> {
	public static final ShopMenu dao = new ShopMenu();

	/**
	 * 获取商城菜单
	 * 
	 * @return
	 */
	public List<ShopMenu> getAllMenu() {
		return ShopMenu.dao.find("select * from shop_menu");
	}
}
