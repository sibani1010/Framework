import org.testng.IRetryAnalyzer;

public class RetryAnalyzer implements IRetryAnalyzer{

	
	
	 private static final Logger log = LoggerFactory.getLogger(Retry.class);
	    private int retryCount = 0;
	    private int maxRetryCount = 2;

	    public String getResultStatusName(int status) {
	        String resultName = null;
	        if (status == 1)
	            resultName = "SUCCESS";
	        if (status == 2)
	            resultName = "FAILURE";
	        if (status == 3)
	            resultName = "SKIP";
	        return resultName;
	    }
	 @Override
	    public boolean retry(ITestResult iresult) {
	        if (!iresult.isSuccess()) {
	            if (retryCount < maxRetryCount) {
	                log.info("Retrying test " + result.getName() + " with status " + "'" + getResultStatusName(result.getStatus()) + "'" + " for the " + (retryCount + 1) + " time(s).");
	                result.setStatus(ITestResult.SKIP);
	                retryCount++;
	                return true;
	            }else {
	                result.setStatus(ITestResult.FAILURE);
	            }
	        } else {
	            result.setStatus(ITestResult.SUCCESS);
	        }
	        return false;
	    }
	
}
