package tseo.eobrazovanje.dto;

import javax.validation.constraints.NotNull;

public class PasswordDto {
	
	private String username;
	
	@NotNull
	private String oldPassword;
	@NotNull
	private String newPassword;

	public PasswordDto() {
	}

	public PasswordDto(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
