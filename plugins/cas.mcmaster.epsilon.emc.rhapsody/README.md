
This IModel implementation provides access to Rhapsody projects/models via the Rhapsody JAVA API.

The Rhapsody Application can be running or not. If not running, a new instance will be opened and closed when the model is disposed. If running, the application will not be closed. If a project path is not provided during load, the active project in Rhapsody will be used (if present).

**Note:** Some actions may trigger an UI prompt from the Rhapsody API, execution will be blocked until the dialog is closed by the user. For example, when loading a project
with invalid extension or path.

#Model Loading

The model must be loaded via one of the load methods that accepts a *StringProperties* paramter. The following properties are supported:
 - *install_dir* (`RhapsodyModel#PROPERTIES_INSTALLATION_DIRECTORY`): the path to the Rhapsody installation. Should point to the top folder of the specific version, e.g. 'C:\Program Files\IBM\Rhapsody\9.0.1'.
 - *prj_path* (`RhapsodyModel#PROPERTIES_PROJECT_PATH`):  (optional) the path to the Rhapsody project to use. If absent, the current project opened in Rhapsody will be used (if present).
 - *main_package* (`RhapsodyModel#PROPERTIES_MAIN_PACKAGE_NAME`): (optional) the main package name, defaults to the first package in the model. This package is needed to be able to create new instances.

Type operations (e.g. allOfType, allofKind, createInstance, etc) rely on two sources of information.
i) The <code>metaclasses.txt</code> file that contains the list of supported metaclass (type) names,
and ii) the existing *Stereotype*s in the model. For the latter, only Stereotypes that
are defined as **new terms** will be considered for type related operations.

#Property Access

Property accessors in ETL languages can be used to access both element attributes as well as Stereotype tag values. Element attributes should NOT be confused with the Element's Properties (accessible via `IRPModelElement#getPropertyValue`).

When accessing an attribute, a matching Java method is searched by name with the following combinations, in order of priority:
 - X()
 - getX()
 - isX()
 - hasX()
 
As a result, for example in the case of `IRPModelElement`s, the expression
<code>element.nestedElements</code> will always result in a call to <i>getNestedElements</i>
as opposed to <i>hasNestedElements</i>. To get the hasNestedElements value, you will
need to use <code>element.hasNestedElements</code>.

# Compatibility

The following table shows the version compatibility of the EMC Driver:

EMC Model | Rhapsody 
|---|---|
|1.0.0 | 9.0.1 |


# Development

For development you need access to a Rhapsody Installation. 

## Jar to Plugin

The `cas.mcmaster.epsilon.emc.rhapsody` projects needs the Rhapsody jar as a dependency. 
For this, you need to craete a new project using the `Plug-in From Existing JAR Archives` [wizard](https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.pde.doc.user%2Fguide%2Ftools%2Fproject_wizards%2Fplugin_from_archives.htm).
The name of the project should be `com.telelogic.rhapsody.core`, and the version should match 
the Rhapsody version you are using. For specific versions matching the EMC plugin version see the 
table in the Compatibility section.

Once the plugin is created. Add a `lib` folder to the root and copy the Java API dll from the 
Rhapsody installation to this folder. 

**NOTE:** DO NOT redistribute the plugin you created from the jar or the dll.



