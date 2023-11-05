package it.finalproject_lastversion.DAO.user;

import java.util.Optional;

import java.util.List;
import it.finalproject_lastversion.DAO.IBaseDAO;
import it.finalproject_lastversion.model.User;

public interface UserDAO extends IBaseDAO<User> {
	public Optional<User> findByUsernameAndPassword(String username, String password) throws Exception;
	public Optional<User> findByUsernameAndRoleIsClassicUserWithoutCard(String username) throws Exception;

	public Optional<User> findByUsernameAndRoleIsMerchantUser(String username) throws Exception;

	public List<User> findAllMerchant() throws Exception;
}
