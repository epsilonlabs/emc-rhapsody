/********************************************************************************
 * Copyright (c) 2023 McMaster University
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the Apache License, Version 2.0
 * which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 ********************************************************************************/
package cas.mcmaster.epsilon.emc.tests;

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
	RhapsodyModelTypesTests.class,
	RhapsodyPropertySetterTests.class,
	RhapsodyPropertyGetterTests.class})
public class RhapsodySuite {

}
