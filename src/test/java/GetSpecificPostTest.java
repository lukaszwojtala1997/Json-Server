import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetSpecificPostTest extends BaseTest{

    @Test
    public void getSpecificPost(){
        ExtentTest test = extentReports.createTest("Get specific post");

        given().
                pathParam("postId", 1).
                spec(requestSpecification).
        when().
                get("{postId}").
        then().
                spec(responseSpecification).
                body("author", equalTo("Tomek"));
    }
}
