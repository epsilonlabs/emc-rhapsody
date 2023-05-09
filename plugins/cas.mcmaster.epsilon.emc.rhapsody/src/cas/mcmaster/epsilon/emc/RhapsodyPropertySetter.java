package cas.mcmaster.epsilon.emc;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.java.JavaPropertySetter;

import com.telelogic.rhapsody.core.IRPModelElement;

public class RhapsodyPropertySetter extends JavaPropertySetter {
	
	private final RhapsodyModel model;
	
	public RhapsodyPropertySetter(RhapsodyModel rhapsodyModel) {
		model = rhapsodyModel;
	}

	@Override
	public void invoke(Object target, String property, Object value, IEolContext context) throws EolRuntimeException {
		if (target instanceof IRPModelElement) {
			try {
				var element = (IRPModelElement) target;
				element.setPropertyValue(property, (String) value);
			} catch (Exception e) {
				throw new EolRuntimeException();
			}
			
		} else {
			super.invoke(target, property, value, context);
		}
		
		
	}

}
