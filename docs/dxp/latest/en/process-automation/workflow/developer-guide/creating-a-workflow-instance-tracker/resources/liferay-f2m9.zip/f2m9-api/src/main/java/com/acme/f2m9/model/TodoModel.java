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

package com.acme.f2m9.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Todo service. Represents a row in the &quot;F2M9_Todo&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.acme.f2m9.model.impl.TodoModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.acme.f2m9.model.impl.TodoImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Todo
 * @generated
 */
@ProviderType
public interface TodoModel
	extends BaseModel<Todo>, GroupedModel, ShardedModel, StagedAuditedModel,
			WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a todo model instance should use the {@link Todo} interface instead.
	 */

	/**
	 * Returns the primary key of this todo.
	 *
	 * @return the primary key of this todo
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this todo.
	 *
	 * @param primaryKey the primary key of this todo
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this todo.
	 *
	 * @return the uuid of this todo
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this todo.
	 *
	 * @param uuid the uuid of this todo
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the todo ID of this todo.
	 *
	 * @return the todo ID of this todo
	 */
	public long getTodoId();

	/**
	 * Sets the todo ID of this todo.
	 *
	 * @param todoId the todo ID of this todo
	 */
	public void setTodoId(long todoId);

	/**
	 * Returns the group ID of this todo.
	 *
	 * @return the group ID of this todo
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this todo.
	 *
	 * @param groupId the group ID of this todo
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this todo.
	 *
	 * @return the company ID of this todo
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this todo.
	 *
	 * @param companyId the company ID of this todo
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this todo.
	 *
	 * @return the user ID of this todo
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this todo.
	 *
	 * @param userId the user ID of this todo
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this todo.
	 *
	 * @return the user uuid of this todo
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this todo.
	 *
	 * @param userUuid the user uuid of this todo
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this todo.
	 *
	 * @return the user name of this todo
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this todo.
	 *
	 * @param userName the user name of this todo
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this todo.
	 *
	 * @return the create date of this todo
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this todo.
	 *
	 * @param createDate the create date of this todo
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this todo.
	 *
	 * @return the modified date of this todo
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this todo.
	 *
	 * @param modifiedDate the modified date of this todo
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the status of this todo.
	 *
	 * @return the status of this todo
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this todo.
	 *
	 * @param status the status of this todo
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this todo.
	 *
	 * @return the status by user ID of this todo
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this todo.
	 *
	 * @param statusByUserId the status by user ID of this todo
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this todo.
	 *
	 * @return the status by user uuid of this todo
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this todo.
	 *
	 * @param statusByUserUuid the status by user uuid of this todo
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this todo.
	 *
	 * @return the status by user name of this todo
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this todo.
	 *
	 * @param statusByUserName the status by user name of this todo
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this todo.
	 *
	 * @return the status date of this todo
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this todo.
	 *
	 * @param statusDate the status date of this todo
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the name of this todo.
	 *
	 * @return the name of this todo
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this todo.
	 *
	 * @param name the name of this todo
	 */
	public void setName(String name);

	/**
	 * Returns <code>true</code> if this todo is approved.
	 *
	 * @return <code>true</code> if this todo is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this todo is denied.
	 *
	 * @return <code>true</code> if this todo is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this todo is a draft.
	 *
	 * @return <code>true</code> if this todo is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this todo is expired.
	 *
	 * @return <code>true</code> if this todo is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this todo is inactive.
	 *
	 * @return <code>true</code> if this todo is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this todo is incomplete.
	 *
	 * @return <code>true</code> if this todo is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this todo is pending.
	 *
	 * @return <code>true</code> if this todo is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this todo is scheduled.
	 *
	 * @return <code>true</code> if this todo is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public Todo cloneWithOriginalValues();

}