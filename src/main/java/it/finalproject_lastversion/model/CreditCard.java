package it.finalproject_lastversion.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "credit_card")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private String id;

	@NotNull
	@Column(name = "card_number", unique = true, length = 16)
	private String cardNumber;

	@NotNull
	@Min(0)
	@Column(name = "balance")
	private double balance;

	@OneToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "ref_card_holder", referencedColumnName = "id")
	private User cardHolder;

	@NotNull
	@Column(name = "enabled", columnDefinition = "TINYINT(1) default 1")
	private boolean enabled;
	// any card can contain several transactions which should be different to each
	// other
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
	private Set<Transaction> transactions = new HashSet<>();

	public CreditCard() {
		super();
	}

	// It may be useful for searching by Id
	public CreditCard(String id) {
		super();
		this.id = id;
	}
	public CreditCard(String cardNumber,double balance, User cardHolder, boolean enabled) {
		super();
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.cardHolder = cardHolder;
		this.enabled = enabled;
	}
	public CreditCard(String id, String cardNumber, double balance) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.balance = balance;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(User cardHolder) {
		this.cardHolder = cardHolder;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "CreditCard{" +
				"cardNumber='" + cardNumber + '\'' +
				'}';
	}
}
