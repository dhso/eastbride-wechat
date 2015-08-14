package modules.codepad.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Codepad implements Serializable {
	private static final long serialVersionUID = -3134720687910504403L;
	private Integer id;
	private Integer pid;
	private String text;
	private String iconCls;
	private String state;
	private CodepadAttributes attributes;
	private List<Codepad> children = new ArrayList<Codepad>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public CodepadAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(CodepadAttributes attributes) {
		this.attributes = attributes;
	}

	public List<Codepad> getChildren() {
		return children;
	}

	public void setChildren(List<Codepad> children) {
		this.children = children;
	}

}
