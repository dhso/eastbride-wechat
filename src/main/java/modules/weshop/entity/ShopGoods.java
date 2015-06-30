package modules.weshop.entity;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class ShopGoods extends Model<ShopGoods> {
	public static final ShopGoods dao = new ShopGoods();

	/**
	 * 获取所有商品
	 * 
	 * @return
	 */
	public List<ShopGoods> getAllGoods() {
		return ShopGoods.dao.find("select * from shop_goods");
	}

	/**
	 * 获取商品详细信息
	 * 
	 * @param id
	 * @return
	 */
	public ShopGoods fetchGoodsDetail(int goodsId) {
		return ShopGoods.dao.findById(goodsId, "name,image,detail,price,old_price");
	}
}
