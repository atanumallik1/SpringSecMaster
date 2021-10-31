package com.springsecurity.ch16.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "client_id")
	private String clientId;

	private String secret;

	@Column(name = "grant_type")
	private String grantType;

	private String scope;

	public int getId() {
		return id;
	}

	public String getClientId() {
		return clientId;
	}

	public String getSecret() {
		return secret;
	}

	public String getGrantType() {
		return grantType;
	}

	public String getScope() {
		return scope;
	}

}
