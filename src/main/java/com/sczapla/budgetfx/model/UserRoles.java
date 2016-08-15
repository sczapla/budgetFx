package com.sczapla.budgetfx.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserRoles implements Serializable {

	@EmbeddedId
	private UserRolesPK userRolePK;

	public UserRolesPK getUserRolePK() {
		return userRolePK;
	}

	public void setUserRolePK(UserRolesPK userRolePK) {
		this.userRolePK = userRolePK;
	}

}
