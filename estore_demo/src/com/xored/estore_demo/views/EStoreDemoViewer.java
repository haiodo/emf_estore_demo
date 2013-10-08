package com.xored.estore_demo.views;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import com.xored.estore_demo.DemoFactory;
import com.xored.estore_demo.DemoRoot;
import com.xored.estore_demo.Organization;
import com.xored.estore_demo.Person;
import com.xored.estore_demo.ResourceBasedEStoreModel;

public class EStoreDemoViewer extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.xored.estore_demo.views.ModelViewer";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		public Object getParent(Object child) {

			return null;
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof Organization) {
				return ((Organization) parent).getPeople().toArray();
			}
			if (parent instanceof DemoRoot) {
				return ((DemoRoot) parent).getOrganizations().toArray();
			}
			if (parent instanceof Person) {
				return ((Person) parent).getLikedToWork().toArray();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent) {
			return getChildren(parent).length > 0;
		}
	}

	/**
	 * The constructor.
	 */
	public EStoreDemoViewer() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	private ResourceBasedEStoreModel model = null;
	private DemoFactory factory = null;
	private IFile currentFile = null;

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.VIRTUAL);
		viewer.getTree().setHeaderVisible(true);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());

		TreeViewerColumn nameColumn = new TreeViewerColumn(viewer, 0);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Organization) {
					return ((Organization) element).getName();
				} else if (element instanceof Person) {
					return ((Person) element).getFirstName();
				}
				return "";
			}
		});
		TreeViewerColumn typeColumn = new TreeViewerColumn(viewer, 0);
		typeColumn.getColumn().setText("Type");
		typeColumn.getColumn().setWidth(100);
		typeColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof EObject) {
					return ((EObject) element).eClass().getName();
				}
				return "unknown";
			}
		});

		TreeViewerColumn dataColumn = new TreeViewerColumn(viewer, 0);
		dataColumn.getColumn().setText("Value");
		dataColumn.getColumn().setWidth(100);
		dataColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Organization) {
					return ((Organization) element).getAddress();
				} else if (element instanceof Person) {
					return ((Person) element).getSecondName()
							+ ((Person) element).getSomeReallyBigData()
									.length();
				}
				return "";
			}
		});
		TreeViewerColumn bigData = new TreeViewerColumn(viewer, 0);
		bigData.getColumn().setText("Value");
		bigData.getColumn().setWidth(1000);
		bigData.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Person) {
					return ((Person) element).getSomeReallyBigData();
				}
				return "";
			}
		});

		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();

		PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService()
				.addSelectionListener(new ISelectionListener() {
					@Override
					public void selectionChanged(IWorkbenchPart part,
							ISelection selection) {
						if (viewer.getControl().isDisposed()) {
							PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow()
							.getSelectionService()
							.removeSelectionListener(this);
						}
						if (selection instanceof IStructuredSelection) {
							final Object firstElement = ((IStructuredSelection) selection)
									.getFirstElement();
							if (firstElement instanceof IFile
									&& ((IFile) firstElement).getName()
											.toLowerCase()
											.endsWith(".demomodel")) {
								if (firstElement.equals(currentFile)) {
									return;
								}
								currentFile = (IFile) firstElement;
								viewer.getControl().getDisplay()
										.asyncExec(new Runnable() {
											@Override
											public void run() {
												model = new ResourceBasedEStoreModel(
														(IFile) firstElement);
												factory = new DemoFactory(model);
												viewer.setInput(model.getRoot());
											}
										});
							}
						}
					}
				});
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				EStoreDemoViewer.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				if (obj instanceof Person) {
					showMessage("Person BIGData:"
							+ ((Person) obj).getSomeReallyBigData());
				}
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				"Model Viewer", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}