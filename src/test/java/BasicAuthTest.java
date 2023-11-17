import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicAuthTest extends BaseTest{

    @Test
    public void basicAuth(){
        ExtentTest test = extentReports.createTest("Basic auth");

        given().auth().basic("postman","password").
        when().get("https://postman-echo.com/basic-auth").then().log().all();
    }
}
