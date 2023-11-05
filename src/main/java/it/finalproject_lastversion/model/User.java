package it.finalproject_lastversion.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

	// Non ho inserito alcun riferimento alla carta nello User:
	// i possessori di carta saranno solo gli utenti della tipologia
	// ROLE_CLASSIC_USER

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private String id;

	@NotNull
	@Column(name = "username", unique = true)
	private String username;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private Role userRole;

	@NotNull
	@Column(name = "enabled", columnDefinition = "TINYINT(1) default 1")
	private boolean enabled;

	// in the case the user is merchant_user, he should be able to find every
	// transactions he made, if the user is a classic user he can find his own
	// transactions in his card
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
	private Set<Transaction> transactions = new HashSet<>();

	public User() {
		super();
	}

	// It may be useful for searching by Id
	public User(String id) {
		super();
		this.id = id;
	}

	public User(String id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public User(String username, String password, Role userRole) {
		super();
		this.password = password;
		this.username = username;
		this.userRole = userRole;
	}

	// Getters and Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userRole=" + userRole + ", enabled="
				+ enabled + "]";
	}

}
