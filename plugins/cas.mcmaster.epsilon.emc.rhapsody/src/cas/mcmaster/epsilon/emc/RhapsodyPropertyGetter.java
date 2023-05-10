package cas.mcmaster.epsilon.emc;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.java.JavaPropertyGetter;

import com.telelogic.rhapsody.core.IRPModelElement;

public class RhapsodyPropertyGetter extends JavaPropertyGetter {
	
	private final RhapsodyModel model;
	
	public RhapsodyPropertyGetter(RhapsodyModel rhapsodyModel) {
		model = rhapsodyModel;
	}

	@Override
	public Object invoke(Object object, String property, IEolContext context) throws EolRuntimeException {
		
		if (object instanceof IRPModelElement) {
			try {
				var element = (IRPModelElement) object;
				return element.getPropertyValue(property);
			} catch (Exception e) {
				throw new EolRuntimeException();
			}
			
		}
		
		return super.invoke(object, property, context);
	}

}
