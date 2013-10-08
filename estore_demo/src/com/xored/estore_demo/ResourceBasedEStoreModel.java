package com.xored.estore_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import com.xored.estore_demo.impl.Estore_demoFactoryImpl;

public class ResourceBasedEStoreModel implements InternalEObject.EStore {
	private IFile indexFile;
	private int objIndex;
	private ModelEntry rootEntry;
	private WeakHashMap<String, ModelEntry> entriesCache = new WeakHashMap<String, ModelEntry>();
	private IFolder objectsRoot;

	public ResourceBasedEStoreModel(IFile indexFile) {
		this.indexFile = indexFile;
		objectsRoot = this.indexFile.getParent().getFolder(
				new Path("objects_" + indexFile.getName()));
		if (!objectsRoot.exists()) {
			try {
				objectsRoot.create(true, true, new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		rootEntry = new ModelEntry(indexFile);
		objIndex = rootEntry.getInt("index");
		if (objIndex == 0) {
			objIndex++;
		}
		rootEntry.save();
	}

	private synchronized ModelEntry getEntry(InternalEObject object) {
		String id = object.eProxyURI().devicePath();
		ModelEntry modelEntry = entriesCache.get(id);
		if (modelEntry != null) {
			return modelEntry;
		}

		// Load entry from file
		IFile file = this.objectsRoot.getFile(object.eClass().getName() + id
				+ ".demo");
		ModelEntry e = new ModelEntry(file);
		entriesCache.put(id, e);
		return e;
	}

	@Override
	public Object get(InternalEObject object, EStructuralFeature feature,
			int index) {

		ModelEntry entry = getEntry(object);
		if (feature instanceof EAttribute) {
			return entry.getValue(object, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			EReference ref = (EReference) feature;
			List<String> property = entry.getList(feature.getName());
			if (property.size() > index) {
				String id = property.get(index);
				return getObjectWithID(ref, id);
			}
		}
		return null;
	}

	private Object getObjectWithID(EReference ref, String id) {
		EObject obj = internalCreate(ref.getEReferenceType());
		((InternalEObject) obj).eSetProxyURI(URI.createFileURI(id));
		return obj;
	}

	@Override
	public Object set(InternalEObject object, EStructuralFeature feature,
			int index, Object value) {

		ModelEntry entry = getEntry(object);
		if (feature instanceof EAttribute) {
			entry.setValue((EAttribute) feature, value, index);
		} else if (feature instanceof EReference) {
			List<String> property = entry.getList(feature.getName());
			if (value instanceof EObject) {
				property.set(index, ((InternalEObject) value).eProxyURI()
						.devicePath());
			}
			entry.setList(feature.getName(), property);
		}
		entry.save();

		return null;
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		ModelEntry entry = getEntry(object);
		return entry.containsKey(feature.getName());
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		ModelEntry entry = getEntry(object);
		entry.remove(feature.getName());
		entry.save();
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			return entry.getList(feature.getName()).isEmpty();
		}
		return false;
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			return entry.getList(feature.getName()).size();
		}
		return 0;
	}

	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature,
			Object value) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			List<String> property = entry.getList(feature.getName());
			if (value instanceof EObject) {
				return property.contains(((InternalEObject) value).eProxyURI()
						.devicePath());
			}
		}
		return false;
	}

	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature,
			Object value) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			List<String> property = entry.getList(feature.getName());
			if (value instanceof EObject) {
				return property.indexOf(((InternalEObject) value).eProxyURI()
						.devicePath());
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature,
			Object value) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			List<String> property = entry.getList(feature.getName());
			if (value instanceof EObject) {
				return property.lastIndexOf(((InternalEObject) value)
						.eProxyURI().devicePath());
			}
		}
		return -1;
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature,
			int index, Object value) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			List<String> property = entry.getList(feature.getName());
			if (value instanceof EObject) {
				property.add(index, ((InternalEObject) value).eProxyURI()
						.devicePath());
			}
			entry.setList(feature.getName(), property);
			entry.save();
		}
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature,
			int index) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			List<String> property = entry.getList(feature.getName());
			String value = property.remove(index);
			Object result = getObjectWithID((EReference) feature, value);
			entry.setList(feature.getName(), property);
			entry.save();
			return result;
		}
		return null;
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature,
			int targetIndex, int sourceIndex) {
		return null;
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		remove(object, feature, 0);
	}

	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			List<String> property = entry.getList(feature.getName());
			List<Object> objects = new ArrayList<Object>();
			for (String id : property) {
				objects.add(getObjectWithID((EReference) feature, id));
			}
			return objects.toArray();
		}
		return new Object[0];
	}

	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature,
			T[] array) {
		ModelEntry entry = getEntry(object);
		if (feature instanceof EReference) {
			List<String> property = entry.getList(feature.getName());
			List<Object> objects = new ArrayList<Object>();
			for (String id : property) {
				objects.add(getObjectWithID((EReference) feature, id));
			}
			return objects.toArray(array);
		}
		return null;
	}

	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		return (((InternalEObject) object).eProxyURI().devicePath() + feature
				.getName()).hashCode();
	}

	@Override
	public InternalEObject getContainer(InternalEObject object) {
		return null;
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		return null;
	}

	@Override
	public EObject create(EClass eClass) {
		InternalEObject eobj = internalCreate(eClass);

		synchronized (this) {
			eobj.eSetProxyURI(org.eclipse.emf.common.util.URI.createFileURI(""
					+ objIndex));
			objIndex++;
			rootEntry.setProperty("index", Integer.toString(objIndex));
			rootEntry.save();
		}
		return eobj;
	}

	private InternalEObject internalCreate(EClass eClass) {
		InternalEObject eobj = (InternalEObject) Estore_demoFactoryImpl.eINSTANCE
				.create(eClass);
		eobj.eSetStore(this);
		return eobj;
	}

	public DemoRoot getRoot() {
		InternalEObject eobj = internalCreate(Estore_demoPackage.eINSTANCE
				.getDemoRoot());
		synchronized (this) {
			eobj.eSetProxyURI(org.eclipse.emf.common.util.URI
					.createFileURI("0"));
		}
		return (DemoRoot) eobj;
	}
}
