import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetPostsTest extends BaseTest{

    @Test
    public void getPosts(){
        ExtentTest test = extentReports.createTest("Get posts");

       given().
                spec(requestSpecification).
       when().
                get().
       then().
                spec(responseSpecification);
    }
}
