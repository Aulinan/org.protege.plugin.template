package template.tab;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.protege.editor.owl.ui.OWLWorkspaceViewsTab;

public class CSVViewerTab extends OWLWorkspaceViewsTab {
	private static final Logger log = LogManager.getLogger(CSVViewerTab.class);

	public CSVViewerTab() {
		setToolTipText("Custom tooltip text for Example Tab (2)");
	}

	@Override
	public void initialise() {
		super.initialise();
		log.info("Example Workspace Tab (2) initialized");
	}

	@Override
	public void dispose() {
		super.dispose();
		log.info("Example Workspace Tab (2) disposed");
	}
}
