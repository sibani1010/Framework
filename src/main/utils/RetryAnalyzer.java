import org.testng.IRetryAnalyzer;

public class RetryAnalyzer implements IRetryAnalyzer{

	    int count = 0;
	    int retryCount = 1;
	    
	 @Override
	    public boolean retry(ITestResult iresult) {
	        while(count<retryCount)
	        	count++;
	    }
	
}
