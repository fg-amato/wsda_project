package it.finalproject_lastversion.DAO;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;

public interface IBaseDAO<T> {
	public List<T> list() throws Exception;

	public Optional<T> findOne(String id) throws Exception;

	public void update(T input) throws Exception;

	public void insert(T input) throws Exception;

	public void delete(T input) throws Exception;

	// questo mi serve per l'injection
	public void setEntityManager(EntityManager entityManager);

}
