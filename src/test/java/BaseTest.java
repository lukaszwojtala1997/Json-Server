import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BaseTest {


    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extentReports;

    @BeforeSuite
    private void setUp(){
        htmlReporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://localhost:3000").
                setBasePath("posts").
                setContentType(ContentType.JSON).build();
        responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).build().
                statusCode(anyOf(is(200),is(201)));

        ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter();
        RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter();
        RestAssured.filters(responseLoggingFilter, requestLoggingFilter);

    }

    @AfterSuite
    public void afterSuite(){
        htmlReporter.flush();
        extentReports.flush();
    }

}
