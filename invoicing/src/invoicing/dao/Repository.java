package invoicing.dao;

import java.util.Collection;
import java.util.Optional;

import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;

public interface Repository<K,V extends Identifiable<K>> {
	Optional<V> findById(K id);
	Collection<V> findAll();
	V add(V entity) throws EntityExistsException ;
	V update(V entity) throws EntityDoesNotExistException;
	Optional<V> delete(K id);
	default void setIdGenerator(IdAutoIncrementable<K> idGen) {}
	default IdAutoIncrementable<K> getIdGenerator() { return null;}
}