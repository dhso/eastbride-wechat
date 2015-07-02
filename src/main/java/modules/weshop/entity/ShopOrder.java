package modules.weshop.entity;

import java.util.Date;
import java.util.List;

import modules.wechat.model.WxCustomer;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import frame.kit.IdentityKit;
import frame.kit.StringKit;

@SuppressWarnings("serial")
public class ShopOrder extends Model<ShopOrder> {
	public static final ShopOrder dao = new ShopOrder();

	/**
	 * 分页获取订单
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param openid
	 * @return
	 */
	public Page<ShopOrder> getOrderPage(int pageNumber, int pageSize, String openid) {
		return ShopOrder.dao.paginate(pageNumber, pageSize, "select *", "from shop_order as so left join customer as cst on cst.open_id = so.open_id where cst.open_id = ? order by so.create_dt desc", openid);
	}

	/**
	 * 添加订单
	 * 
	 * @param openid
	 * @param total_price
	 * @param pay_style
	 * @param order_note
	 * @param cartdata
	 * @param create_id
	 * @param true_name
	 * @param mobile
	 * @param address
	 */
	public void addOrder(String openid, String total_price, String pay_style, String order_note, String cartdata, String create_id, String true_name, String mobile, String address) {
		WxCustomer customer = WxCustomer.dao.findById(openid);
		if (StringKit.toFloat(customer.getStr("money")) >= StringKit.toFloat(total_price)) {
			new ShopOrder().set("order_id", IdentityKit.uuid4()).set("open_id", openid).set("total_price", total_price).set("pay_style", pay_style).set("pay_status", "1").set("order_status", "0").set("order_note", order_note).set("cartdata", cartdata).set("create_id", create_id)
					.set("create_dt", new Date()).save();
		} else {
			new ShopOrder().set("order_id", IdentityKit.uuid4()).set("open_id", openid).set("total_price", total_price).set("pay_style", pay_style).set("pay_status", "0").set("order_status", "0").set("order_note", order_note).set("cartdata", cartdata).set("create_id", create_id)
					.set("create_dt", new Date()).save();
		}
		customer.set("true_name", true_name).set("mobile", mobile).set("address", address).set("money", String.valueOf(StringKit.toFloat(customer.getStr("money")) - StringKit.toFloat(total_price))).update();
	}

	/**
	 * 删除订单
	 * 
	 * @param openid
	 * @param orderid
	 */
	public void delOrder(String openid, String orderid) {
		WxCustomer customer = WxCustomer.dao.findById(openid);
		ShopOrder shopOrder = ShopOrder.dao.findById(orderid);
		customer.set("money", String.valueOf(StringKit.toFloat(customer.getStr("money")) + StringKit.toFloat(shopOrder.getStr("total_price")))).save();
		shopOrder.delete();
	}

	/**
	 * 获取将要处理的订单
	 * 
	 * @return
	 */
	public List<Record> getToDoOrders() {
		
		return Db
				.find("select so.order_id as orderId,so.total_price as totalPrice,so.order_data as orderData,so.create_dt as createDt,so.order_note as orderNote,so.pay_status as payStatus,so.order_status as orderStatus, su.name as userName from shop_order so left join shop_user su on su.open_id = so.open_id where so.order_status !='2' order by so.create_dt desc");
	}

	/**
	 * 设置将要处理的订单的支付状态
	 * 
	 * @param orderId
	 * @param payStatus
	 * @return
	 */
	public int payOrder(String orderId, String payStatus) {
		return Db.update("update shop_order set pay_status = ? where order_id = ?", payStatus, orderId);
	}

	/**
	 * 设置将要处理的订单的成交状态
	 * 
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	public int dealOrder(String orderId, String orderStatus) {
		return Db.update("update shop_order set order_status = ? where order_id = ?", orderStatus, orderId);
	}

	/**
	 * 关闭将要处理的订单
	 * 
	 * @param orderId
	 * @return
	 */
	public int closeOrder(String orderId) {
		return Db.update("update shop_order set order_status = '2' where order_id = ?", orderId);
	}
}
