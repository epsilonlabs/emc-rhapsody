package cas.mcmaster.showcase;

import java.io.File;

import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.execute.context.Variable;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

public class ShowcaseEpsilon {

	public static void main(String[] args) throws Exception {
		RhapsodyModel model = new RhapsodyModel();
		EolModule module = new EolModule(); 
		module.getContext().getModelRepository().addModel(model);
		File file = new File("eol.eol");
		module.parse(file);
		model.load();
		module.execute();
		model.dispose();
	}

}
