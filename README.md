# API_UI_Automation_JAVA

to run 
`prerequisites`: Have maven, java, jdk installed and in PATH.
```
clone this repo
mvn clean test -DsuiteXmlFile=testng.xml
```

Notes:
1. This runs API and UI in Parallel
2. The BaseAutomationMethods class is intended to have common methods across API and UI test methods
3. The response is deserialized with POJO. We are telling our code not to use hand coded codes or JSON strings rather a strongly typed string which are less error prone.​
4. Schema Validation for all the requests
5. BaseAPI is an Abstract class which will enforce API Classes to implement the key methods. By this was we avoide writing givens, in test files and rather have methods to set query parameters, headers, so on
```
	public void perform() {
		createRequest();
		executeRequest();
		validateResponse();
	}
```

