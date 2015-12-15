/**
 * 
 */
/**
 * @author hadong
 *
 */
package modules.system.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -8765640214072557201L;
	private String mmbMembershipnum;
	private String cstName;
	private String cstMobile;
	private String xcsIdcardnum;
	private String preferStore;
	private String rewardtype;
	private Integer period;
	private Integer mmbid;
	private Integer cstId;
	private String cntupdateFlag;

	public User() {

	}

	public User(String mmbMembershipnum, String cstName, String cstMobile, String xcsIdcardnum, String preferStore, String rewardtype, Integer period, Integer mmbid, Integer cstId, String cntupdateFlag) {
		this.mmbMembershipnum = mmbMembershipnum;
		this.cstName = cstName;
		this.cstMobile = cstMobile;
		this.xcsIdcardnum = xcsIdcardnum;
		this.preferStore = preferStore;
		this.rewardtype = rewardtype;
		this.period = period;
		this.mmbid = mmbid;
		this.cstId = cstId;
		this.cntupdateFlag = cntupdateFlag;
	}

	public String getMmbMembershipnum() {
		return mmbMembershipnum;
	}

	public String getCstName() {
		return cstName;
	}

	public String getCstMobile() {
		return cstMobile;
	}

	public String getXcsIdcardnum() {
		return xcsIdcardnum;
	}

	public String getPreferStore() {
		return preferStore;
	}

	public String getRewardtype() {
		return rewardtype;
	}

	public Integer getPeriod() {
		return period;
	}

	public Integer getMmbid() {
		return mmbid;
	}

	public Integer getCstId() {
		return cstId;
	}

	public String getCntupdateFlag() {
		return cntupdateFlag;
	}

	public void setMmbMembershipnum(String mmbMembershipnum) {
		this.mmbMembershipnum = mmbMembershipnum;
	}

	public void setCstName(String cstName) {
		this.cstName = cstName;
	}

	public void setCstMobile(String cstMobile) {
		this.cstMobile = cstMobile;
	}

	public void setXcsIdcardnum(String xcsIdcardnum) {
		this.xcsIdcardnum = xcsIdcardnum;
	}

	public void setPreferStore(String preferStore) {
		this.preferStore = preferStore;
	}

	public void setRewardtype(String rewardtype) {
		this.rewardtype = rewardtype;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public void setMmbid(Integer mmbid) {
		this.mmbid = mmbid;
	}

	public void setCstId(Integer cstId) {
		this.cstId = cstId;
	}

	public void setCntupdateFlag(String cntupdateFlag) {
		this.cntupdateFlag = cntupdateFlag;
	}

}