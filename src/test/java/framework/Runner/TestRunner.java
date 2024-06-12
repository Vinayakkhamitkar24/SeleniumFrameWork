package framework.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber->  TestNG, junit

@CucumberOptions(features="src/test/java/cucumber",glue="framework.stepDefinations",
monochrome=true, tags = "@Regression", plugin= {"html:target/cucumber.html"})
public class TestRunner extends AbstractTestNGCucumberTests{

	
}