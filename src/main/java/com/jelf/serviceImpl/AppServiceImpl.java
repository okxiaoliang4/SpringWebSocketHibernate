package com.jelf.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jelf.dao.Dao;
import com.jelf.service.AppService;

@Service("appService")
public class AppServiceImpl implements AppService {

	@Autowired
	private Dao dao;

	@Override
	public <T> boolean isPropertyValueRepeated(Class<T> target, Long id,
			String property, Object value) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq(property, value));
		if (id != null) {
			criterions.add(Restrictions.ne("id", id));
		}
		return dao.count(target, criterions) > 0;
	}
}
