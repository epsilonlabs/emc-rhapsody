package cas.mcmaster.tests;

import static org.junit.jupiter.api.Assertions.fail;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

/**
 * To run this tests, Rhapsody needs to be opened with a loaded project.
 * @author Horacio Hoyos Rodriguez
 *
 */
public class RhapsodyModelTestsActivePrj {
	
	@AfterEach
	void close() {
		if(this.model != null) {
			this.model.dispose();
		}
	}
	
	@Test
	void loads_active_project_if_path_missing() {
		model = new RhapsodyModel();
		try {
			StringProperties properties = new StringProperties();
			properties.put(RhapsodyModel.PROPERTIES_INSTALLATION_DIRECTORY, "path/to/rhapsody");
			model.load(properties);
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
	}
	
	
	private IModel model;
	
}
