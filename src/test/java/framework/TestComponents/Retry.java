package framework.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult result) {
		int count =0;
		int maxRetry=1;
		
		if(count<maxRetry)
		{
			return true;
		}
		return false;
	}

}
