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

package com.acme.t2p5.model.impl;

import com.acme.t2p5.model.T2P5Entry;
import com.acme.t2p5.model.T2P5EntryModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the T2P5Entry service. Represents a row in the &quot;T2P5_T2P5Entry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>T2P5EntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link T2P5EntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see T2P5EntryImpl
 * @generated
 */
public class T2P5EntryModelImpl
	extends BaseModelImpl<T2P5Entry> implements T2P5EntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a t2p5 entry model instance should use the <code>T2P5Entry</code> interface instead.
	 */
	public static final String TABLE_NAME = "T2P5_T2P5Entry";

	public static final Object[][] TABLE_COLUMNS = {
		{"t2p5EntryId", Types.BIGINT}, {"description", Types.VARCHAR},
		{"name", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("t2p5EntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table T2P5_T2P5Entry (t2p5EntryId LONG not null primary key,description VARCHAR(75) null,name VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table T2P5_T2P5Entry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY t2p5Entry.t2p5EntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY T2P5_T2P5Entry.t2p5EntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long T2P5ENTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public T2P5EntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _t2p5EntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setT2p5EntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _t2p5EntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return T2P5Entry.class;
	}

	@Override
	public String getModelClassName() {
		return T2P5Entry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<T2P5Entry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<T2P5Entry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<T2P5Entry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((T2P5Entry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<T2P5Entry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<T2P5Entry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(T2P5Entry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<T2P5Entry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<T2P5Entry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, T2P5Entry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			T2P5Entry.class.getClassLoader(), T2P5Entry.class,
			ModelWrapper.class);

		try {
			Constructor<T2P5Entry> constructor =
				(Constructor<T2P5Entry>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<T2P5Entry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<T2P5Entry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<T2P5Entry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<T2P5Entry, Object>>();
		Map<String, BiConsumer<T2P5Entry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<T2P5Entry, ?>>();

		attributeGetterFunctions.put("t2p5EntryId", T2P5Entry::getT2p5EntryId);
		attributeSetterBiConsumers.put(
			"t2p5EntryId",
			(BiConsumer<T2P5Entry, Long>)T2P5Entry::setT2p5EntryId);
		attributeGetterFunctions.put("description", T2P5Entry::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<T2P5Entry, String>)T2P5Entry::setDescription);
		attributeGetterFunctions.put("name", T2P5Entry::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<T2P5Entry, String>)T2P5Entry::setName);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getT2p5EntryId() {
		return _t2p5EntryId;
	}

	@Override
	public void setT2p5EntryId(long t2p5EntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_t2p5EntryId = t2p5EntryId;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, T2P5Entry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public T2P5Entry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, T2P5Entry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		T2P5EntryImpl t2p5EntryImpl = new T2P5EntryImpl();

		t2p5EntryImpl.setT2p5EntryId(getT2p5EntryId());
		t2p5EntryImpl.setDescription(getDescription());
		t2p5EntryImpl.setName(getName());

		t2p5EntryImpl.resetOriginalValues();

		return t2p5EntryImpl;
	}

	@Override
	public T2P5Entry cloneWithOriginalValues() {
		T2P5EntryImpl t2p5EntryImpl = new T2P5EntryImpl();

		t2p5EntryImpl.setT2p5EntryId(
			this.<Long>getColumnOriginalValue("t2p5EntryId"));
		t2p5EntryImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		t2p5EntryImpl.setName(this.<String>getColumnOriginalValue("name"));

		return t2p5EntryImpl;
	}

	@Override
	public int compareTo(T2P5Entry t2p5Entry) {
		long primaryKey = t2p5Entry.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof T2P5Entry)) {
			return false;
		}

		T2P5Entry t2p5Entry = (T2P5Entry)object;

		long primaryKey = t2p5Entry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<T2P5Entry> toCacheModel() {
		T2P5EntryCacheModel t2p5EntryCacheModel = new T2P5EntryCacheModel();

		t2p5EntryCacheModel.t2p5EntryId = getT2p5EntryId();

		t2p5EntryCacheModel.description = getDescription();

		String description = t2p5EntryCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			t2p5EntryCacheModel.description = null;
		}

		t2p5EntryCacheModel.name = getName();

		String name = t2p5EntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			t2p5EntryCacheModel.name = null;
		}

		return t2p5EntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<T2P5Entry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<T2P5Entry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<T2P5Entry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((T2P5Entry)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<T2P5Entry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<T2P5Entry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<T2P5Entry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((T2P5Entry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, T2P5Entry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _t2p5EntryId;
	private String _description;
	private String _name;

	public <T> T getColumnValue(String columnName) {
		Function<T2P5Entry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((T2P5Entry)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("t2p5EntryId", _t2p5EntryId);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("name", _name);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("t2p5EntryId", 1L);

		columnBitmasks.put("description", 2L);

		columnBitmasks.put("name", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private T2P5Entry _escapedModel;

}