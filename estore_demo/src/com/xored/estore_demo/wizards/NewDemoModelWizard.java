package com.xored.estore_demo.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import com.xored.estore_demo.DemoFactory;
import com.xored.estore_demo.DemoRoot;
import com.xored.estore_demo.Organization;
import com.xored.estore_demo.Person;
import com.xored.estore_demo.ResourceBasedEStoreModel;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the
 * provided container. If the container resource (a folder or a project) is
 * selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "demomodel".
 * If a sample multi-page editor (also available as a template) is registered
 * for the same extension, it will be able to open it.
 */

public class NewDemoModelWizard extends Wizard implements INewWizard {
	private NewDemoModelWizardPage page;
	private ISelection selection;

	/**
	 * Constructor for NewDemoModelWizard.
	 */
	public NewDemoModelWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new NewDemoModelWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		final boolean generateData = page.isGenerateSample();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor, generateData);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error",
					realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * The worker method. It will find the container, create the file if missing
	 * or just replace its contents, and open the editor on the newly created
	 * file.
	 * 
	 * @param generateData
	 */

	private void doFinish(String containerName, String fileName,
			IProgressMonitor monitor, boolean generateData)
			throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName
					+ "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		ResourceBasedEStoreModel model = new ResourceBasedEStoreModel(file);
		DemoFactory factory = new DemoFactory(model);
		DemoRoot demoRoot = factory.createDemoRoot();

		if (!generateData) {
			Organization organization = factory.createOrganization();
			demoRoot.getOrganizations().add(organization);
		}
		else {
			Random r = new Random();
			monitor.beginTask("Create demo model", 100);

			SubProgressMonitor mon = new SubProgressMonitor(monitor, 50);

			// Create organizations
			int orgs = r.nextInt(100) + 100;
			mon.beginTask("Create organizations", orgs);
			for (int i = 0; i < orgs; i++) {
				Organization org = factory.createOrganization();
				org.setAddress("Some addresss: " + r.nextDouble());
				org.setName("Org_" + i);
				demoRoot.getOrganizations().add(org);
				mon.worked(1);
			}

			mon.done();
			mon = new SubProgressMonitor(monitor, 50);
			// Create random persons
			int persons = r.nextInt(1000) + 1000;
			mon.beginTask("Create persons", persons);
			for (int i = 0; i < persons; i++) {
				Person person = factory.createPerson();
				person.setFirstName("Name_" + i);
				person.setSecondName("SurName" + r.nextInt());

				int bigdata = r.nextInt(1024) + 1024;
				StringBuilder str = new StringBuilder();
				for (int j = 0; j < bigdata; j++) {
					str.append(Integer.toString(r.nextInt(), 16));
				}
				person.setSomeReallyBigData(str.toString());

				int orgCount = demoRoot.getOrganizations().size();
				
				Organization org = demoRoot.getOrganizations().get(
						r.nextInt(orgCount - 1));
				
				if (r.nextInt() % 2 == 0) {
					person.getLikedToWork().add(org);
				}
				Organization likeOrg = demoRoot.getOrganizations().get(
						r.nextInt(orgCount - 1));
				if (r.nextInt() % 2 == 0) {
					person.getLikedToWork().add(likeOrg);
				}
				org.getPeople().add(person);

				demoRoot.getPeople().add(person);
				mon.worked(1);
			}
		}
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, "estore_demo", IStatus.OK,
				message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}