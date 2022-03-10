package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.annotations.ITestAnnotation;

public class SuiteListener implements ITestListener, IAnnotationTransformer {
	@Override
	
	public void onTestStart(ITestListener iTestResult)
	{
		
	}
    @Override
	public void onTestSuccess(ITestListener iTestResult)
	{
		
	}
    @Override
	public void onTestFailure(ITestListener iTestResult)
	{
    	
    	String fileName = System.getProperty("user.dir")+ File.separator + "screenshots"+ File.separator+ iTestResult.getMethod().getMethodName();
    	File f= ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
    	try{
    		FileUtils.copyFile(f, new File(fileName +".png"));
    	}catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    	
    	
		
	}
    @Override
	public void onTestSkipped(ITestListener iTestResult)
	{
		
	}
    @Override
   	public void onTestFailedButWithSuccessPercentage(ITestListener iTestResult)
   	{
   		
   	}
    @Override
	public void onStart(ITestListener iTestContext)
	{
		
	}
    @Override
	public void onFinish(ITestListener iTestContext)
	{
		
	}
    @Override
    pubic void transform(ITestAnnotation iTestAnnotation,Class aClass, Constructor constructor, Metod method){
    	iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
    
    

}
