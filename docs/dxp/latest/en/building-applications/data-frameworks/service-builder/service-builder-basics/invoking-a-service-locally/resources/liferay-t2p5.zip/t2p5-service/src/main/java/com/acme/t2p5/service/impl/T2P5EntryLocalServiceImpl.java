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

package com.acme.t2p5.service.impl;

import com.acme.t2p5.model.T2P5Entry;
import com.acme.t2p5.service.base.T2P5EntryLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.acme.t2p5.model.T2P5Entry",
	service = AopService.class
)
public class T2P5EntryLocalServiceImpl extends T2P5EntryLocalServiceBaseImpl {

	public T2P5Entry addT2P5Entry(String description, String name)
		throws PortalException {

		T2P5Entry t2p5Entry = t2p5EntryPersistence.create(
			counterLocalService.increment());

		t2p5Entry.setDescription(description);
		t2p5Entry.setName(name);

		return t2p5EntryPersistence.update(t2p5Entry);
	}

}