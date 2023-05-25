package cas.mcmaster.epsilon.emc;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.exceptions.EolIllegalPropertyException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.java.ObjectMethod;
import org.eclipse.epsilon.eol.execute.operations.contributors.OperationContributorRegistry;

import com.telelogic.rhapsody.core.IRPInstanceValue;
import com.telelogic.rhapsody.core.IRPLiteralSpecification;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPTag;

public class RhapsodyPropertyGetter implements IPropertyGetter {

	@Override
	public boolean hasProperty(Object object, String property, IEolContext context) {
		try (ObjectMethod om = getMethodFor(object, property, context)) {
			if (om.getMethod() != null) {
				return true;
			}
		}
		// Can be a stereotype tag?
		IRPModelElement element = (IRPModelElement) object;
		return element.getTag(property) != null;
	}
	
	@Override
	public Object invoke(
		Object target,
		String property,
		IEolContext context) throws EolRuntimeException {
		if (!(target instanceof IRPModelElement)) {
			throw new IllegalArgumentException("Can't get ptoperty of none IRPModelElement");
		}
		try (ObjectMethod objectMethod = getMethodFor(target, property, context)) {
			if (objectMethod.getMethod() != null) {
				ModuleElement ast = context.getExecutorFactory().getActiveModuleElement();
				return objectMethod.execute(ast, context);
			}
		}
		// Can be a stereotype tag?
		IRPModelElement element = (IRPModelElement) target;
		IRPTag tag = element.getTag(property);
		if (tag == null) {
			LOG.error("Could not find a property or tag with name {}", property);
			throw new EolIllegalPropertyException(
					target,
					property,
					context.getExecutorFactory().getActiveModuleElement(),
					context);
		}
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
	
	private static final Logger LOG = LogManager.getLogger(RhapsodyPropertyGetter.class);

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
	
	private ObjectMethod getMethodFor(Object object, String property, IEolContext context) {
		OperationContributorRegistry registry = context.getOperationContributorRegistry();
		
		// Look for an X() method
		ObjectMethod om = registry.findContributedMethodForEvaluatedParameters(object, property, new Object[]{}, context);
		if (om != null) return om;
				
		// Look for a getX() method
		om = registry.findContributedMethodForEvaluatedParameters(object, "get" + property, new Object[]{}, context);
		if (om != null) return om;

		// Look for an isX() method
		om = registry.findContributedMethodForEvaluatedParameters(object, "is" + property, new Object[]{}, context);
		if (om != null) return om;
		
		// Look for an hasX() method
		om = registry.findContributedMethodForEvaluatedParameters(object, "has" + property, new Object[]{}, context);
		if (om != null) return om;
		
		return new ObjectMethod(object);
	}

}
