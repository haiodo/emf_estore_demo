package com.xored.estore_demo;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.xored.estore_demo.impl.Estore_demoFactoryImpl;

public class DemoFactory extends Estore_demoFactoryImpl {
	private ResourceBasedEStoreModel model;

	public DemoFactory(ResourceBasedEStoreModel model) {
		this.model = model;
	}

	@Override
	public EObject create(EClass eClass) {
		return this.model.create(eClass);
	}

	@Override
	public DemoRoot createDemoRoot() {
		return this.model.getRoot();
	}

	@Override
	public Organization createOrganization() {
		return (Organization) create(Estore_demoPackage.eINSTANCE
				.getOrganization());
	}

	@Override
	public Person createPerson() {
		return (Person) create(Estore_demoPackage.eINSTANCE.getPerson());
	}

}
