package com.bae.raziel.login;


public class LoginDto {


	private String razielUser;
	private String razielPassword;

	private int status;
	
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
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LoginDto [razielUser=" + razielUser + ", razielPassword=" + razielPassword + ", status=" + status + "]";
	}

	
	
}
