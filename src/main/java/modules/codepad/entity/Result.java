package modules.codepad.entity;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 1651525560155147152L;

	private String resCode;

	private Object resDesc;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public Object getResDesc() {
		return resDesc;
	}

	public void setResDesc(Object resDesc) {
		this.resDesc = resDesc;
	}

	public Result() {

	}

	public Result(String resCode, Object resDesc) {
		this.resCode = resCode;
		this.resDesc = resDesc;
	}
}
