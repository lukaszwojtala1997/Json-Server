import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTest extends BaseTest{

    @Test
    public void getPost(){
        ExtentTest test = extentReports.createTest("Get post from postSchema.json");

        when().
                        get("http://localhost:3000/posts/{postId}", 1).
                then().
                        assertThat().body(matchesJsonSchemaInClasspath("postSchema.json"));
    }
}
