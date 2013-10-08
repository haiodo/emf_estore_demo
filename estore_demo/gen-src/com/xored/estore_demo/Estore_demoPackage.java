/**
 */
package com.xored.estore_demo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.xored.estore_demo.Estore_demoFactory
 * @model kind="package"
 * @generated
 */
public interface Estore_demoPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "estore_demo";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///com/xored/estore_demo.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "com.xored.estore_demo";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Estore_demoPackage eINSTANCE = com.xored.estore_demo.impl.Estore_demoPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.xored.estore_demo.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.estore_demo.impl.PersonImpl
	 * @see com.xored.estore_demo.impl.Estore_demoPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 0;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__FIRST_NAME = 0;

	/**
	 * The feature id for the '<em><b>Second Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__SECOND_NAME = 1;

	/**
	 * The feature id for the '<em><b>Liked To Work</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__LIKED_TO_WORK = 2;

	/**
	 * The feature id for the '<em><b>Some Really Big Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__SOME_REALLY_BIG_DATA = 3;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.xored.estore_demo.impl.OrganizationImpl <em>Organization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.estore_demo.impl.OrganizationImpl
	 * @see com.xored.estore_demo.impl.Estore_demoPackageImpl#getOrganization()
	 * @generated
	 */
	int ORGANIZATION = 1;

	/**
	 * The feature id for the '<em><b>People</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION__PEOPLE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION__NAME = 1;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION__ADDRESS = 2;

	/**
	 * The number of structural features of the '<em>Organization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Organization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link com.xored.estore_demo.impl.DemoRootImpl <em>Demo Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.estore_demo.impl.DemoRootImpl
	 * @see com.xored.estore_demo.impl.Estore_demoPackageImpl#getDemoRoot()
	 * @generated
	 */
	int DEMO_ROOT = 2;

	/**
	 * The feature id for the '<em><b>Organizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEMO_ROOT__ORGANIZATIONS = 0;

	/**
	 * The feature id for the '<em><b>People</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEMO_ROOT__PEOPLE = 1;

	/**
	 * The number of structural features of the '<em>Demo Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEMO_ROOT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Demo Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEMO_ROOT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link com.xored.estore_demo.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see com.xored.estore_demo.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.estore_demo.Person#getFirstName <em>First Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Name</em>'.
	 * @see com.xored.estore_demo.Person#getFirstName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_FirstName();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.estore_demo.Person#getSecondName <em>Second Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Second Name</em>'.
	 * @see com.xored.estore_demo.Person#getSecondName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_SecondName();

	/**
	 * Returns the meta object for the reference list '{@link com.xored.estore_demo.Person#getLikedToWork <em>Liked To Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Liked To Work</em>'.
	 * @see com.xored.estore_demo.Person#getLikedToWork()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_LikedToWork();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.estore_demo.Person#getSomeReallyBigData <em>Some Really Big Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Some Really Big Data</em>'.
	 * @see com.xored.estore_demo.Person#getSomeReallyBigData()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_SomeReallyBigData();

	/**
	 * Returns the meta object for class '{@link com.xored.estore_demo.Organization <em>Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Organization</em>'.
	 * @see com.xored.estore_demo.Organization
	 * @generated
	 */
	EClass getOrganization();

	/**
	 * Returns the meta object for the reference list '{@link com.xored.estore_demo.Organization#getPeople <em>People</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>People</em>'.
	 * @see com.xored.estore_demo.Organization#getPeople()
	 * @see #getOrganization()
	 * @generated
	 */
	EReference getOrganization_People();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.estore_demo.Organization#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.xored.estore_demo.Organization#getName()
	 * @see #getOrganization()
	 * @generated
	 */
	EAttribute getOrganization_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.estore_demo.Organization#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see com.xored.estore_demo.Organization#getAddress()
	 * @see #getOrganization()
	 * @generated
	 */
	EAttribute getOrganization_Address();

	/**
	 * Returns the meta object for class '{@link com.xored.estore_demo.DemoRoot <em>Demo Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Demo Root</em>'.
	 * @see com.xored.estore_demo.DemoRoot
	 * @generated
	 */
	EClass getDemoRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link com.xored.estore_demo.DemoRoot#getOrganizations <em>Organizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Organizations</em>'.
	 * @see com.xored.estore_demo.DemoRoot#getOrganizations()
	 * @see #getDemoRoot()
	 * @generated
	 */
	EReference getDemoRoot_Organizations();

	/**
	 * Returns the meta object for the containment reference list '{@link com.xored.estore_demo.DemoRoot#getPeople <em>People</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>People</em>'.
	 * @see com.xored.estore_demo.DemoRoot#getPeople()
	 * @see #getDemoRoot()
	 * @generated
	 */
	EReference getDemoRoot_People();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Estore_demoFactory getEstore_demoFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.xored.estore_demo.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.estore_demo.impl.PersonImpl
		 * @see com.xored.estore_demo.impl.Estore_demoPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__FIRST_NAME = eINSTANCE.getPerson_FirstName();

		/**
		 * The meta object literal for the '<em><b>Second Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__SECOND_NAME = eINSTANCE.getPerson_SecondName();

		/**
		 * The meta object literal for the '<em><b>Liked To Work</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__LIKED_TO_WORK = eINSTANCE.getPerson_LikedToWork();

		/**
		 * The meta object literal for the '<em><b>Some Really Big Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__SOME_REALLY_BIG_DATA = eINSTANCE.getPerson_SomeReallyBigData();

		/**
		 * The meta object literal for the '{@link com.xored.estore_demo.impl.OrganizationImpl <em>Organization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.estore_demo.impl.OrganizationImpl
		 * @see com.xored.estore_demo.impl.Estore_demoPackageImpl#getOrganization()
		 * @generated
		 */
		EClass ORGANIZATION = eINSTANCE.getOrganization();

		/**
		 * The meta object literal for the '<em><b>People</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORGANIZATION__PEOPLE = eINSTANCE.getOrganization_People();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORGANIZATION__NAME = eINSTANCE.getOrganization_Name();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORGANIZATION__ADDRESS = eINSTANCE.getOrganization_Address();

		/**
		 * The meta object literal for the '{@link com.xored.estore_demo.impl.DemoRootImpl <em>Demo Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.estore_demo.impl.DemoRootImpl
		 * @see com.xored.estore_demo.impl.Estore_demoPackageImpl#getDemoRoot()
		 * @generated
		 */
		EClass DEMO_ROOT = eINSTANCE.getDemoRoot();

		/**
		 * The meta object literal for the '<em><b>Organizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEMO_ROOT__ORGANIZATIONS = eINSTANCE.getDemoRoot_Organizations();

		/**
		 * The meta object literal for the '<em><b>People</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEMO_ROOT__PEOPLE = eINSTANCE.getDemoRoot_People();

	}

} //Estore_demoPackage
