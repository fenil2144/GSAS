package com.gsas.model;

public class LoginVO {
	
	private long loginId;
	private String userName;
	private String password;
	private boolean isEmployee;
	
	public LoginVO() {
		super();
	}

	public LoginVO(long loginId, String userName, String password, boolean isEmployee) {
		super();
		this.loginId = loginId;
		this.userName = userName;
		this.password = password;
		this.isEmployee = isEmployee;
	}

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
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

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	@Override
	public String toString() {
		return "LoginVO [loginId=" + loginId + ", userName=" + userName + ", password=" + password + ", isEmployee="
				+ isEmployee + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isEmployee ? 1231 : 1237);
		result = prime * result + (int) (loginId ^ (loginId >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginVO other = (LoginVO) obj;
		if (isEmployee != other.isEmployee)
			return false;
		if (loginId != other.loginId)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


	
}
