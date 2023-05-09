package cas.mcmaster.showcase;

import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

import com.telelogic.rhapsody.core.IRPModelElement;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

public class Showcase {

	public static void main(String[] args) throws EolModelLoadingException {
		RhapsodyModel api = new RhapsodyModel(); 
		api.load();
		
		System.out.println("The current project is: " + api.getCurrentProjectName());
		api.addPackage("test0");
		api.addPackage("test1");
		api.addPackage("test2");
		
		if(api.setPackage("test0")) {
			System.out.println("Package changed sucessfully");
		}
		api.addType("type_0");
		api.addProperty("type_0", "test");
		String result = api.readProperty("type_0", "CG.Type.test");
		System.out.println("Initial value of property is: " + result);
		api.setProperty("type_0", "CG.Type.test", "False");
		String result2 = api.readProperty("type_0", "CG.Type.test");
		System.out.println("Updated value of property is: " + result2);
		
		System.out.println("Printing the names of all elements");
		var contents = api.allContents().toArray();
		for (int i = 0; i < contents.length; i++) {
			System.out.println(((IRPModelElement)contents[i]).getName());
		}
		if(api.checkIfChanged()) {
			System.out.println("Model has been updated");
		}
		api.close();

	}

}
