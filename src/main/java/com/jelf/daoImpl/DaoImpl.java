package com.jelf.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jelf.dao.Dao;

@Repository("Dao")
public class DaoImpl implements Dao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unused")
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void executeUpdateNativeSQL(String sql) {
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void saveOrUpdate(Object entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public <T> boolean delete(Class<T> target, Serializable id) {
		return delete(sessionFactory.getCurrentSession().get(target, id));
	}

	@Override
	public boolean delete(Object object) {
		if (object != null) {
			sessionFactory.getCurrentSession().delete(object);
			return true;
		}
		return false;
	}

	private <T> T newInstance(Class<T> target) {
		try {
			return target.newInstance();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T findById(Class<T> target, Serializable id) {
		if (id == null) {
			return newInstance(target);
		}
		T obj = (T) sessionFactory.getCurrentSession().get(target, id);
		return obj != null ? obj : newInstance(target);
	}

	@Override
	public <T> List<T> list(Class<T> target) {
		return list(target, new ArrayList<Criterion>(),
				Arrays.asList(Order.asc("id")));
	}

	@Override
	public <T> List<T> list(Class<T> target, Order order) {
		return list(target, new ArrayList<Criterion>(), Arrays.asList(order));
	}

	@Override
	public <T> List<T> list(Class<T> target,
			List<? extends Criterion> criterions) {
		return list(target, criterions, new ArrayList<Order>());
	}

	@Override
	public <T> List<T> list(Class<T> target,
			List<? extends Criterion> criterions, Order order) {
		return list(target, criterions, Arrays.asList(order));
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<T> target,
			List<? extends Criterion> criterions, List<Order> orders) {
		return createCriteria(target, criterions, orders).list();
	}

	private <T> Criteria createCriteria(Class<T> target,
			List<? extends Criterion> criterions, List<Order> orders) {
		return createCriteria(target, null, criterions, orders);
	}

	@Override
	public <T> List<T> list(Class<T> target, T exampleInstance) {
		return list(target, exampleInstance, new ArrayList<Criterion>(),
				new ArrayList<Order>());
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<T> target, T exampleInstance,
			List<? extends Criterion> criterions, List<Order> orders) {
		return createCriteria(target, exampleInstance, criterions, orders)
				.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<T> target, T exampleInstance,
			List<? extends Criterion> criterions, List<Order> orders,
			int startIndex, int endIndex) {
		return createCriteria(target, exampleInstance, criterions, orders,
				startIndex, endIndex).list();
	}

	private <T> Criteria createCriteria(Class<T> target, T exampleInstance,
			List<? extends Criterion> criterions, List<Order> orders) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				target);
		if (exampleInstance != null) {
			criteria.add(Example.create(exampleInstance)
					.enableLike(MatchMode.ANYWHERE).ignoreCase());
		}
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return criteria;
	}

	private <T> Criteria createCriteria(Class<T> target, T exampleInstance,
			List<? extends Criterion> criterions, List<Order> orders,
			int startIndex, int endIndex) {
		Criteria criteria = createCriteria(target, exampleInstance, criterions,
				orders);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(endIndex - startIndex + 1);
		return criteria;
	}

	@Override
	public <T> int count(Class<T> target) {
		return count(target, null, new ArrayList<Criterion>());
	}

	@Override
	public <T> int count(Class<T> target, List<? extends Criterion> criterions) {
		return count(target, null, criterions);
	}

	@Override
	public <T> int count(Class<T> target, T exampleInstance) {
		return count(target, exampleInstance, new ArrayList<Criterion>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> int count(Class<T> target, T exampleInstance,
			List<? extends Criterion> criterions) {
		int total = 0;
		List<Number> numbers = createCriteria(target, exampleInstance,
				criterions, new ArrayList<Order>()).setProjection(
				Projections.rowCount()).list();
		for (Number n : numbers) {
			total += n.intValue();
		}
		return total;
		// Number number = (Number) createCriteria(target, exampleInstance,
		// criterions, new ArrayList<Order>()).setProjection(
		// Projections.rowCount()).uniqueResult();
		// return number.intValue();
	}

	public <T> Number sum(Class<T> target,
			List<? extends Criterion> criterions, String propertyName) {
		return (Number) createCriteria(target, null, criterions,
				new ArrayList<Order>()).setProjection(
				Projections.sum(propertyName)).uniqueResult();
	}

	@Override
	public <T> T findOne(Class<T> target, List<? extends Criterion> criterions) {
		List<T> list = list(target, criterions);
		return list.isEmpty() ? newInstance(target) : list.get(0);
	}

	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> target, List<? extends Criterion> criterions) {
		return (T) createCriteria(target, criterions, new ArrayList<Order>())
				.uniqueResult();
	}

	@Override
	public <T> List<?> listByProjection(Class<T> target, Criterion criterion,
			Order order, Projection projection) {
		return listByProjection(target, Arrays.asList(criterion),
				Arrays.asList(order), projection);
	}

	@Override
	public <T> List<?> listByProjection(Class<T> target,
			List<? extends Criterion> criterions, List<Order> orders,
			Projection projection) {
		return createCriteria(target, null, criterions, orders).setProjection(
				projection).list();
	}
}