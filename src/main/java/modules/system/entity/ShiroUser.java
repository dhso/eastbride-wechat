package modules.system.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShiroUser implements Serializable {

	private static final long serialVersionUID = 1199273565468095881L;

	private Long id;
	private String userName;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String salt;
	private Integer locked;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

}
