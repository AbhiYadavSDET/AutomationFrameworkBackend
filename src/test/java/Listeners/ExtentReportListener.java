package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;

    static {
        // Set up the Extent report using ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Start the test and log the name
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Mark test as passed
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Mark test as failed and add exception if any
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Mark test as skipped
        test.skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Write the report to file
        extent.flush();
    }
}
