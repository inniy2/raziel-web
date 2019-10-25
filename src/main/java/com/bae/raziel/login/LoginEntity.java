package com.bae.raziel.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "raziel_user")
public class LoginEntity {
	
	
	@Id
	@Column(name = "razie_user", nullable = false)
	private String razielUser;
	
	@Column(name = "raziel_password", nullable = false)
	private String razielPassword;

	
	public String getRazielUser() {
		return razielUser;
	}

	public void setRazielUser(String razielUser) {
		this.razielUser = razielUser;
	}

	public String getRazielPassword() {
		return razielPassword;
	}

	public void setRazielPassword(String razielPassword) {
		this.razielPassword = razielPassword;
	}

	@Override
	public String toString() {
		return "RazielUserEntity [razielUser=" + razielUser + ", razielPassword=" + razielPassword + "]";
	}
	
	


}
