package cas.mcmaster.epsilon.emc;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.java.JavaPropertyGetter;

import com.telelogic.rhapsody.core.IRPInstanceValue;
import com.telelogic.rhapsody.core.IRPLiteralSpecification;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPTag;

public class RhapsodyPropertyGetter extends JavaPropertyGetter {

	@Override
	public Object invoke(Object object, String property, IEolContext context) throws EolRuntimeException {
		if (!(object instanceof IRPModelElement)) {
			throw new IllegalArgumentException("Can't get ptoperty of none IRPModelElement");
		}
		try {
			return super.invoke(object, property, context);
		} catch (EolRuntimeException e) {
			// Can be a stereotype tag?
			IRPModelElement element = (IRPModelElement) object;
			IRPTag tag = element.getTag(property);
			var valSpecs = tag.getValueSpecifications();
			var looper = valSpecs.toList().iterator();
			var result = new ArrayList<Object>();
			while(looper.hasNext()) {
				var valSpec = looper.next();
				if (valSpec instanceof IRPInstanceValue) {
					result.add(((IRPInstanceValue)valSpec).getValue());
				} else if (valSpec instanceof IRPLiteralSpecification) {
					result.add( getLiteralSpecValue(tag, (IRPLiteralSpecification) valSpec));
				} else {
					throw new IllegalStateException("Unknown value specification type: " + valSpec.getClass().getName());
				}
			}
			if (result.size() == 1) {
				return result.get(0);
			} else {
				return result;
			}
		}
	}

	private Object getLiteralSpecValue(IRPTag tag, IRPLiteralSpecification valSpec) {
		var valSpecVal = valSpec.getValue();
		var tagTypeName = tag.getType().getName();
		Object result = null;
		try {
			switch(tagTypeName) {
				case "RhpReal":
					result = Float.parseFloat(valSpecVal);
					break;
				case "RhpInteger":
					result = Integer.parseInt(valSpecVal);
					break;
				case "RhpBoolean":
					result = Boolean.parseBoolean(valSpecVal);
					break;
				case "RhpString":
					result = valSpecVal;
					break;
				default:
					LOG.warn("Primtive value not considered: {}", tagTypeName);
					result = valSpecVal;
			}
		} catch (NumberFormatException e1) {
			LOG.warn("Unable to parse the tag value as specified type: {} as {}. Will return string value.", valSpecVal, tagTypeName);
			result = valSpecVal;
		}
		return result;
	}
	
	private static final Logger LOG = LogManager.getLogger(RhapsodyPropertyGetter.class);

}
