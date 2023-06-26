package cas.mcmaster.epsilon.emc.rhapsody.dt;

import org.eclipse.epsilon.common.dt.launching.dialogs.AbstractCachedModelConfigurationDialog;
import org.eclipse.epsilon.common.dt.util.DialogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class RhapsodyModelConfigurationDialog extends AbstractCachedModelConfigurationDialog {
	
	private Text fileText;
	private boolean selectElemRoot;
	private Text rhapsodyText;

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
		createRhapsodyInstallationGroup(control);
	}
	
	private void createFileGroup(Composite parent) {
		final Composite groupContent = DialogUtil.createGroupContainer(parent, "Files", 3);
		
		final Label modelFileLabel = new Label(groupContent, SWT.NONE);
		modelFileLabel.setText("Model file: ");
		
		fileText = new Text(groupContent, SWT.BORDER);
		fileText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		final Button browseFile = new Button(groupContent, SWT.NONE);
		browseFile.setText("Browse Workspace...");
		browseFile.addListener(SWT.Selection, new BrowseWorkspaceForModelsListener(fileText, "Rhapsody model files in the workspace",
				"Select a Rhapsody model file"));
		final Label useCurrentProjLabel = new Label(groupContent, SWT.NONE);
		useCurrentProjLabel.setText("Use current project: ");
		final Button useCurrentProjectCheckBtn = new Button(groupContent, SWT.CHECK);
		useCurrentProjectCheckBtn.addSelectionListener(new CurrentProjectListener(fileText));
	}
	
	private void createRhapsodyInstallationGroup(Composite parent) {
		final Composite groupContent = DialogUtil.createGroupContainer(parent, "Rhapsody Installation", 3);
		
		final Label modelFileLabel = new Label(groupContent, SWT.NONE);
		modelFileLabel.setText("Rhapsody installation: ");
		
		fileText = new Text(groupContent, SWT.BORDER);
		fileText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		final Button browseFile = new Button(groupContent, SWT.NONE);
		browseFile.setText("Browse...");
		browseFile.addSelectionListener(new SelectionListener() {
 
			public void widgetDefaultSelected(SelectionEvent e) {
			}
 
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(browseFile.getShell(),  SWT.OPEN);
				dlg.setText("Open");
				String path = dlg.open();
				if (path == null) return;
				fileText.setText(path);
			}
		});
	}
	
	protected class CurrentProjectListener extends SelectionAdapter {
		
		private Text targetText;
		
		public CurrentProjectListener(Text target) {
			targetText = target;
		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			if (((Button) event.getSource()).getSelection()) {
				targetText.setText("current project");
			}
		}
	}
	
}
