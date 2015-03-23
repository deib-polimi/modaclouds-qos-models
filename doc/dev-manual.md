[Documentation table of contents](TOC.md) / Developer Manual

# Developer Manual

## Actions

In order to create a new action for monitoring rules create a new class with the name of your action, extending the `AbstractAction` class, and put it in the `it.polimi.modaclouds.qos_models.monitoring_rules.actions` package.

The action has to override the following methods:
* `Set<Problem> getMyRequiredPars()` returning the list of required parameters that the user should put in the monitoring rule,
* `void execute(String resourceId, String value, String timestamp)` implementing the concrete action to be executed when the rule is evaluated and the action is requested. The method will receive as parameters information about the current evaluation of the rule, i.e., the id of the resource being monitored, the value of the metric and the timestamp.

The action can use the following methods implemented by its super class:
* `Map<String, String> getParameters()` to retrieve all parameters specified by the user in the monitoring rule for the specific action
* `Logger getLogger()` to retrieve the logger
* `String getName()` to retrieve the name of the action (which corresponds the class simple name)