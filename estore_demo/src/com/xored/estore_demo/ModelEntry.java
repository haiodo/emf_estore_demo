package com.xored.estore_demo;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ModelEntry {

	private IFile file;
	private Properties properties = new Properties();

	public ModelEntry(IFile indexFile) {
		this.file = indexFile;
		load();
	}

	public void load() {
		try {
			if (file.exists()) {
				properties.load(new BufferedInputStream(file.getContents()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			properties.store(bout, "");
			ByteArrayInputStream bytes = new ByteArrayInputStream(
					bout.toByteArray());
			if (file.exists()) {
				file.setContents(bytes, true, false, new NullProgressMonitor());
			} else {
				file.create(bytes, true, new NullProgressMonitor());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getInt(String key) {
		return Integer.parseInt(properties.getProperty(key, "0"));
	}

	public Object getValue(InternalEObject object, EAttribute feature, int index) {
		String postfix = "";
		if (index != -1) {
			postfix = "_" + index;
		}
		String value = properties
				.getProperty(feature.getName() + postfix, null);
		if (value != null) {
			return EcoreUtil.createFromString(feature.getEAttributeType(),
					value);
		}
		return null;
	}

	public String getProperty(String name, String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}

	public void setProperty(String name, String value) {
		properties.setProperty(name, value);
	}

	public List<String> getList(String name) {
		String value = getProperty(name, "");
		List<String> result = new ArrayList<String>();
		if (value != null) {
			String[] values = value.split("\n");
			for (String val : values) {
				if( val.trim().length() > 0) {
					result.add(val);
				}
			}
		}
		return result;
	}

	public void setList(String name, List<String> property) {
		StringBuilder value = new StringBuilder();
		int index = 0;
		for (String val : property) {
			if (index != 0) {
				value.append("\n");
			}
			value.append(val.trim().replace("\n", "").replace("\r", ""));
			index++;
		}
		properties.setProperty(name, value.toString());
	}

	public void setValue(EAttribute feature, Object value, int index) {
		String strValue = EcoreUtil.convertToString(
				feature.getEAttributeType(), value);
		String postfix = "";
		if (index != -1) {
			postfix = "_" + index;
		}
		properties.setProperty(feature.getName() + postfix, strValue);
	}

	public boolean containsKey(String name) {
		return properties.containsKey(name);
	}

	public void remove(String name) {
		properties.remove(name);
	}

}
