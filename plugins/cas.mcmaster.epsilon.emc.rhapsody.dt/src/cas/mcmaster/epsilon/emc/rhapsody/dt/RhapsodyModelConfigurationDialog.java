package cas.mcmaster.epsilon.emc.rhapsody.dt;

import org.eclipse.epsilon.common.dt.launching.dialogs.AbstractCachedModelConfigurationDialog;
import org.eclipse.epsilon.common.dt.util.DialogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

public class RhapsodyModelConfigurationDialog extends AbstractCachedModelConfigurationDialog {

	private Text fileText, rhapsodyText, mainPackageText;
	private Button setRootElemButton;

	@Override
	protected String getModelName() {
		// TODO Auto-generated method stub
		return "Rhapsody model";
	}

	@Override
	protected String getModelType() {
		// TODO Auto-generated method stub
		return "Rhapsody";
	}

	@Override
	protected void createGroups(Composite control) {
		super.createGroups(control);
		createFileGroup(control);
		createLoadStoreOptionsGroup(control);
		createRhapsodyGroup(control);
	}

	private void createFileGroup(Composite parent) {
		final Composite groupContent = DialogUtil.createGroupContainer(parent, "Files", 3);

		final Label modelFileLabel = new Label(groupContent, SWT.NONE);
		modelFileLabel.setText("Model file: ");

		fileText = new Text(groupContent, SWT.BORDER);
		fileText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		final Button browseFile = new Button(groupContent, SWT.NONE);
		browseFile.setText("Browse Workspace...");
		browseFile.addListener(SWT.Selection, new BrowseWorkspaceForModelsListener(fileText, "Rhapsody model files in the workspace",
				"Select a Rhapsody project file"));
	}

	private void createRhapsodyGroup(Composite parent) {
		final Composite groupContent = DialogUtil.createGroupContainer(parent, "Rhapsody", 3);

		final Label installationDirLabel = new Label(groupContent, SWT.NONE);
		installationDirLabel.setText("Rhapsody installation directory: ");
		installationDirLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		rhapsodyText = new Text(groupContent, SWT.BORDER);
		rhapsodyText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Button browseFile = new Button(groupContent, SWT.NONE);
		browseFile.setText("Browse...");
		browseFile.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dlg = new DirectoryDialog(browseFile.getShell(),  SWT.OPEN);
				dlg.setText("Select Rhapsody Installation Folder");
				String path = dlg.open();
				if (path == null) return;
				rhapsodyText.setText(path);
			}
		});
		final Label mainPackageNameLabel = new Label(groupContent, SWT.NONE);
		mainPackageNameLabel.setText("Main package name: ");
		mainPackageText = new Text(groupContent, SWT.BORDER);
		mainPackageText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		setRootElemButton = new Button(groupContent, SWT.CHECK);
		setRootElemButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 3, 1));
		setRootElemButton.setText("Selected element as root");
	}
	
	@Override
	protected void loadProperties() {
		super.loadProperties();
		if (properties == null) {
			return;
		}
		setRootElemButton.setSelection(Boolean.parseBoolean(properties.getProperty(RhapsodyModel.PROPERTY_ROOT_ELEM)));
		rhapsodyText.setText(properties.getProperty(RhapsodyModel.PROPERTY_INSTALLATION_DIRECTORY));
		fileText.setText(properties.getProperty(RhapsodyModel.PROPERTY_PROJECT_PATH));
		mainPackageText.setText(properties.getProperty(RhapsodyModel.PROPERTY_MAIN_PACKAGE_NAME));
	}

	@Override
	public void storeProperties() {
		super.storeProperties();
		properties.setProperty(RhapsodyModel.PROPERTY_PROJECT_PATH, fileText.getText());
		properties.setProperty(RhapsodyModel.PROPERTY_INSTALLATION_DIRECTORY, rhapsodyText.getText());
		properties.setProperty(RhapsodyModel.PROPERTY_ROOT_ELEM, String.valueOf(setRootElemButton.getSelection()));
		properties.setProperty(RhapsodyModel.PROPERTY_MAIN_PACKAGE_NAME, mainPackageText.getText());
	}
	
}