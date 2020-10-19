package com.gsas.model;

import java.time.LocalDate;

public class CitizenDetailsVO {
	
	private long citizenDetailsId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String email;
	private long phone;
	private AddressVO addressVO;
	private IncomeGroupVO incomeGroup;
	private ProfessionVO profession;
	private long adharNumber;
	private String pancardNumber;
	private LoginVO loginVO;
	
	public CitizenDetailsVO() {
		super();
	}

	public CitizenDetailsVO(long citizenDetailsId, String firstName, String middleName, String lastName,
			LocalDate dateOfBirth, String gender, String email, long phone, AddressVO addressVO,
			IncomeGroupVO incomeGroup, ProfessionVO profession, long adharNumber, String pancardNumber,
			LoginVO loginVO) {
		super();
		this.citizenDetailsId = citizenDetailsId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.addressVO = addressVO;
		this.incomeGroup = incomeGroup;
		this.profession = profession;
		this.adharNumber = adharNumber;
		this.pancardNumber = pancardNumber;
		this.loginVO = loginVO;
	}

	public long getCitizenDetailsId() {
		return citizenDetailsId;
	}

	public void setCitizenDetailsId(long citizenDetailsId) {
		this.citizenDetailsId = citizenDetailsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public AddressVO getAddressVO() {
		return addressVO;
	}

	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}

	public IncomeGroupVO getIncomeGroup() {
		return incomeGroup;
	}

	public void setIncomeGroup(IncomeGroupVO incomeGroup) {
		this.incomeGroup = incomeGroup;
	}

	public ProfessionVO getProfession() {
		return profession;
	}

	public void setProfession(ProfessionVO profession) {
		this.profession = profession;
	}

	public long getAdharNumber() {
		return adharNumber;
	}

	public void setAdharNumber(long adharNumber) {
		this.adharNumber = adharNumber;
	}

	public String getPancardNumber() {
		return pancardNumber;
	}

	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public LoginVO getLoginVO() {
		return loginVO;
	}

	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressVO == null) ? 0 : addressVO.hashCode());
		result = prime * result + (int) (adharNumber ^ (adharNumber >>> 32));
		result = prime * result + (int) (citizenDetailsId ^ (citizenDetailsId >>> 32));
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((incomeGroup == null) ? 0 : incomeGroup.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loginVO == null) ? 0 : loginVO.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((pancardNumber == null) ? 0 : pancardNumber.hashCode());
		result = prime * result + (int) (phone ^ (phone >>> 32));
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
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
		CitizenDetailsVO other = (CitizenDetailsVO) obj;
		if (addressVO == null) {
			if (other.addressVO != null)
				return false;
		} else if (!addressVO.equals(other.addressVO))
			return false;
		if (adharNumber != other.adharNumber)
			return false;
		if (citizenDetailsId != other.citizenDetailsId)
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (incomeGroup == null) {
			if (other.incomeGroup != null)
				return false;
		} else if (!incomeGroup.equals(other.incomeGroup))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (loginVO == null) {
			if (other.loginVO != null)
				return false;
		} else if (!loginVO.equals(other.loginVO))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (pancardNumber == null) {
			if (other.pancardNumber != null)
				return false;
		} else if (!pancardNumber.equals(other.pancardNumber))
			return false;
		if (phone != other.phone)
			return false;
		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CitizenDetailsVO [citizenDetailsId=" + citizenDetailsId + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", email=" + email + ", phone=" + phone + ", addressVO=" + addressVO + ", incomeGroup=" + incomeGroup
				+ ", profession=" + profession + ", adharNumber=" + adharNumber + ", pancardNumber=" + pancardNumber
				+ ", loginVO=" + loginVO + "]";
	}



	
}
