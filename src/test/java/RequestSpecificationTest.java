import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTest extends BaseTest{

    @Test
    public void getPosts(){

        given().
                spec(requestSpecification).
        when().
                get().
        then().
                spec(responseSpecification);
    }

    @Test
    public void getPost(){

        given().
                spec(requestSpecification).
        when().
                get("/1").
        then().
                spec(responseSpecification);
    }
}
