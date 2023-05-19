package cas.mcmaster.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Rhapsody Test Suite")
@SelectPackages("cas.mcmaster.tests")
@SelectClasses({
	RhapsodyMetaclassesTests.class,
	RhapsodyModelLoadingTests.class,
	RhapsodyModelTests.class,
	RhapsodyModelTypesTests.class})
public class RhapsodySuite {

}
