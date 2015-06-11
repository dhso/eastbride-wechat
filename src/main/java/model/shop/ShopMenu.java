package model.shop;

import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_menu", pkName = "menu_id")
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
