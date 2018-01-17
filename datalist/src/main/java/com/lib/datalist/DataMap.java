package com.lib.datalist;


/**
 * 将数据库数据库封装
 */
public class DataMap extends CaseInsensitiveArrayMap {

	private static final long serialVersionUID = 1L;

	public byte[] getBlob(String key) {
		Object o = get(key);
		if (o == null) {
			return null;
		}
		try {
			Object obj = get(key);
			return (byte[]) obj;
		} catch (ClassCastException e) {
			return null;
		}
	}

	public Boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public boolean getBoolean(String key, Boolean defaultValue) {
		Object o = get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			if (o instanceof Boolean)
				return (Boolean) o;
			else
				return (Boolean.valueOf(o.toString()));
		} catch (ClassCastException e) {
			return defaultValue;
		}
	}

	public String getString(String key) {
		return getString(key, null);
	}

	public String getString(String key, String defaultValue) {
		Object o = get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			if (o instanceof String)
				return (String) o;
			else
				return o.toString();
		} catch (ClassCastException e) {
			return defaultValue;
		}
	}

	public float getFloat(String key) {
		return getFloat(key, 0.0f);
	}

	public float getFloat(String key, float defaultValue) {
		Object o = get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			if (o instanceof Number)
				return ((Number) o).floatValue();
			else
				return (Float.valueOf(o.toString()));
		} catch (ClassCastException e) {
			return defaultValue;
		}
	}

	public double getDouble(String key) {
		return getDouble(key, 0.0);
	}

	public double getDouble(String key, double defaultValue) {
		Object o = get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			if (o instanceof Number)
				return ((Number) o).doubleValue();
			else
				return (Double.valueOf(o.toString()));
		} catch (ClassCastException e) {
			return defaultValue;
		}
	}

	public int getInteger(String key) {
		return getInteger(key, 0);
	}

	public int getInteger(String key, int defaultValue) {
		Object o = get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			if (o instanceof Number)
				return ((Number) o).intValue();
			else
				return (Integer.valueOf(o.toString()));
		} catch (ClassCastException e) {
			return defaultValue;
		}
	}

	public long getLong(String key) {
		return getLong(key, 0);
	}

	public long getLong(String key, long defaultValue) {
		Object o = get(key);
		if (o == null) {
			return defaultValue;
		}
		try {
			if (o instanceof Number)
				return ((Number) o).longValue();
			else
				return (Long.valueOf(o.toString()));
		} catch (ClassCastException e) {
			return defaultValue;
		}
	}

	public void putBlob(String key, byte[] values) {
		put(key, values);
	}

	public void putInteger(String key, Integer value) {
		put(key, value);
	}

	public void putFloat(String key, Float value) {
		put(key, value);
	}

	public void putString(String key, String value) {
		put(key, value);
	}

}

