package com.jelf.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

public interface Dao {

	void executeUpdateNativeSQL(String sql);

	void saveOrUpdate(Object entity);

	<T> boolean delete(Class<T> target, Serializable id);

	boolean delete(Object object);

	<T> T findById(Class<T> target, Serializable id);

	<T> List<T> list(Class<T> target);

	<T> List<T> list(Class<T> target, Order order);

	<T> List<T> list(Class<T> target, List<? extends Criterion> criterions);

	<T> List<T> list(Class<T> target, List<? extends Criterion> criterions,
			Order order);

	<T> List<T> list(Class<T> target, List<? extends Criterion> criterions,
			List<Order> orders);

	<T> List<T> list(Class<T> target, T exampleInstance);

	<T> List<T> list(Class<T> target, T exampleInstance,
			List<? extends Criterion> criterions, List<Order> orders);

	<T> List<T> list(Class<T> target, T exampleInstance,
			List<? extends Criterion> criterions, List<Order> orders,
			int startIndex, int endIndex);

	<T> int count(Class<T> target);

	<T> int count(Class<T> target, List<? extends Criterion> criterions);

	<T> int count(Class<T> target, T exampleInstance);

	<T> int count(Class<T> target, T exampleInstance,
			List<? extends Criterion> criterions);

	<T> Number sum(Class<T> target, List<? extends Criterion> criterions,
			String propertyName);

	// if not found, return new instance.
	<T> T findOne(Class<T> target, List<? extends Criterion> criterions);

	// if not found, return null.
	<T> T find(Class<T> target, List<? extends Criterion> criterions);

	<T> List<?> listByProjection(Class<T> target, Criterion criterion,
			Order order, Projection projection);

	<T> List<?> listByProjection(Class<T> target,
			List<? extends Criterion> criterions, List<Order> orders,
			Projection projection);

}
