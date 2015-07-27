package modules.system.entity;

import java.io.Serializable;

public class ShiroUrlType implements Serializable {

	private static final long serialVersionUID = 1199273565468095881L;

	private String url_type_id;
	private String url_type_name;
	private String url_type_icon;

	public String getUrl_type_id() {
		return url_type_id;
	}

	public void setUrl_type_id(String url_type_id) {
		this.url_type_id = url_type_id;
	}

	public String getUrl_type_name() {
		return url_type_name;
	}

	public void setUrl_type_name(String url_type_name) {
		this.url_type_name = url_type_name;
	}

	public String getUrl_type_icon() {
		return url_type_icon;
	}

	public void setUrl_type_icon(String url_type_icon) {
		this.url_type_icon = url_type_icon;
	}

}
