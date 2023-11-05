package it.finalproject_lastversion.DTO;

import it.finalproject_lastversion.model.Role;
import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/*
Evito di portarmi dietro la password mandando l'utente in pagina
 */
public class UserDTO {

    private String id;

    private String username;

    private Role userRole;

    private boolean enabled;

    // in the case the user is merchant_user, he should be able to find every
    // transactions he made, if the user is a classic user he can find his own
    // transactions in his card
    private Set<TransactionDTO> transactions = new HashSet<>();

    public UserDTO() {
        super();
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

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public boolean isAdmin() {
        return this.userRole.equals(Role.ROLE_ADMIN);
    }

    public boolean isMerchant() {
        return this.userRole.equals(Role.ROLE_MERCHANT);
    }

    public boolean isClassicUser() {
        return this.userRole.equals(Role.ROLE_CLASSIC_USER);
    }

    public static UserDTO createUserDTOFromModel(User user) {
        UserDTO userToReturn = new UserDTO();
        userToReturn.setId(user.getId());
        userToReturn.setUserRole(user.getUserRole());
        userToReturn.setEnabled(user.isEnabled());
        userToReturn.setUsername(user.getUsername());

        return userToReturn;
    }

}
