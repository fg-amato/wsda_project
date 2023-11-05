package it.finalproject_lastversion.service.user;

import java.util.List;

import it.finalproject_lastversion.DAO.user.UserDAO;
import it.finalproject_lastversion.model.User;

public interface UserService {

	public User caricaSingoloElemento(String id) throws Exception;

	public void inserisciNuovo(User userInstance) throws Exception;


	public User accedi(String username, String password) throws Exception;

	public User trovaPerUsernameClassicUserSenzaCard(String username) throws Exception;
	public User trovaPerUsernameMerchantUser(String username) throws Exception;

	public List<User> trovaTuttiIMerchant() throws Exception;
	// per injection
	public void setUserDAO(UserDAO userDAO);

	public void disabilita(String id) throws Exception;

	public void abilita(String id) throws Exception;

}
