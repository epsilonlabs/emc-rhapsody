# Epsilon-Rhapsody (IBM) Integration

Eclipse plugins that extend Epsilon's Model Connectivity (EMC) layer with support for querying and modifying Rhapsody Models using languages of the Epsilon platform to perform activities such as code generation, model validation and model-to-model transformation. The Rhapsody EMC driver supports read/write access to Rhapsody models throught the Rhapsody Java API. Rhapsody MUST be installed in the computer running the Epsilon scripts that use this driver.

This driver has been developed with the support of the McMaster Centre for Software Certification ([McSCert](https://www.mcscert.ca)).

## Model Element Types
 
The driver supports all SysML types supported by the Rhapsody API. Additionally, any **Stereotypes** defined as "NewTerms" can also be used as types.

## Model Element properties and methods

The available properties and methods for model elements are the same provided by the Rhapsody API.
The API documnetation is available [online](https://www.ibm.com/docs/en/engineering-lifecycle-management-suite/design-rhapsody/9.0.1?topic=function-rhapsody-api).
The only difference is that collection access from the EMC is 0-index based (as opposed to the 1-index based provided by the API).


