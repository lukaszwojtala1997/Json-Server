import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetSpecificFieldValueTest extends BaseTest{

    @Test
    public void getPostCheckSpecificField(){
        ExtentTest test = extentReports.createTest("Get post check specific field");

        String author = RestAssured.get("http://localhost:3000/posts/1").
                path("author");

        Assert.assertEquals(author, "Tomek");

    }

    @Test
    public void getPostCheckSpecificFieldJsonPath(){
        ExtentTest test = extentReports.createTest("Get post check specific field json path");

        Response response = RestAssured.get("http://localhost:3000/posts/1");
        JsonPath jsonPath = new JsonPath(response.asString());
        String author = jsonPath.get("author");

        Assert.assertEquals(author, "Tomek");
    }
}
