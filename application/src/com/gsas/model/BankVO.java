package com.gsas.model;

public class BankVO {
	
	private long bankId;
	private String bankName;
	
	public BankVO() {
		super();
	}
	public BankVO(long bankId) {
		super();
		this.bankId = bankId;
	}	
	public BankVO(long bankId, String bankName) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
	}

	public long getBankId() {
		return bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "BankVO [bankId=" + bankId + ", bankName=" + bankName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bankId ^ (bankId >>> 32));
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
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
		BankVO other = (BankVO) obj;
		if (bankId != other.bankId)
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		return true;
	}
	
	
}
