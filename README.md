emf_estore_demo
===============

Demo for EMF EStore feature

This little demo shows how to implement EStore custom storage with EMF.

Storage is very simple it takes Eclipse Resources IFile and store all objects into separate files.

# How to test demo.
1. Create new "New EStore Demo Model" via Eclipse new file wizard.
2. Check generate checkbox.
3. Wait for generation.
4. Open "EStore Demo" and select generated file in project explorer.
5. View and navigate for data.

# Implementation details:
1. com.xored.estore_demo.ModelEntry
Persistence layer based on top of Java Properties class.
2. com.xored.estore_demo.ResourceBasedEStoreModel
EStore interface implementation. Most of sugar are here.
3. com.xored.estore_demo.DemoFactory
Object factory to set estore to objects.

# How EStore are work.
Then we specify featureDelegation="Reflective" in our genmodel, EMF will delegate all operations to EStore class. EStore class itself contain set/get and so on methods with signature (InternalEObject, EStructuralFeature, Object..) so it is possible to set/get data from any source. In this example this is file based source. It could be DB and so on.

Because of this Objects itself are just a handles to real data.
In sample I've used proxyURI of InternalEObject class to do a object identity.

# Generated EMF editors.
I've also make generated EMF editors to work with this model. They are not functional but allow to modify created sample models easy. Editors could be opened on .demomodel files.

# Screenshots
![Demo](/docs/working.png "Working")



