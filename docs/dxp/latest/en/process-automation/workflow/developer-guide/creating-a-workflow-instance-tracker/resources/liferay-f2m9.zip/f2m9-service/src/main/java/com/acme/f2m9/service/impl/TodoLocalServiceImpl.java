/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.acme.f2m9.service.impl;

import com.acme.f2m9.model.Todo;
import com.acme.f2m9.service.base.TodoLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.acme.f2m9.model.Todo",
	service = AopService.class
)
public class TodoLocalServiceImpl extends TodoLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	public Todo addTodo(
		long companyId, long groupId, long userId, String userName,
		String item) {

		Todo todo = todoPersistence.create(counterLocalService.increment());

		todo.setCompanyId(companyId);
		todo.setGroupId(groupId);
		todo.setName(item);
		todo.setUserId(userId);
		todo.setUserName(userName);

		return todoPersistence.update(todo);
	}

}