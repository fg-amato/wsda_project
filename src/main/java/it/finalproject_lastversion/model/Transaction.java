package it.finalproject_lastversion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_state")
    private TransactionState transactionState;

    // the one who executes the transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_merchant", nullable = false)
    private User merchant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_credit_card", nullable = false)
    private CreditCard card;
    @NotNull
    @Column(name = "date_transaction")
    private Date dateTransaction;

    public Transaction() {
        super();
    }

    public Transaction(String id) {
        super();
        this.id = id;
    }

    public Transaction(Double amount, User merchant, CreditCard card) {
        super();
        this.amount = amount;
        this.merchant = merchant;
        this.card = card;
        this.dateTransaction = new Date();
    }

    public Transaction(Double amount, User merchant, CreditCard card, TransactionState transactionState) {
        super();
        this.amount = amount;
        this.merchant = merchant;
        this.card = card;
        this.dateTransaction = new Date();
        this.transactionState = transactionState;
    }

    public Transaction(String id, Double amount, User merchant, CreditCard card) {
        super();
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.card = card;
        this.dateTransaction = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getMerchant() {
        return merchant;
    }

    public void setMerchant(User merchant) {
        this.merchant = merchant;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", card=" + card +
                ", dateTransaction=" + dateTransaction +
                '}';
    }
}
