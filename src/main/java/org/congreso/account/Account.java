package org.congreso.account;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
@NamedQueries({
    @NamedQuery(name = Account.FIND_ALL, query = "SELECT a FROM Account a"),
    @NamedQuery(name = Account.FIND_BY_USERNAME, query = "SELECT a FROM Account a WHERE a.username = :username")})
public class Account implements java.io.Serializable {

	public static final String FIND_BY_USERNAME = "Account.findByUsername";
	public static final String FIND_ALL = "Account.findAll";

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String username;
	
	@JsonIgnore
	private String password;

	private String role = "ROLE_USER";
	
	private String name;
	
	protected Account() {

	}
	
	public Account(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
