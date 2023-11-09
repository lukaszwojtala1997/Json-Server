import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetSpecificFieldValueTest extends BaseTest{

    @Test
    public void getPostCheckSpecificField(){

        String author = RestAssured.get("http://localhost:3000/posts/1").
                path("author");


        Assert.assertEquals(author, "Tomek");

    }

    @Test
    public void getPostCheckSpecificFieldJsonPath(){

        Response response = RestAssured.get("http://localhost:3000/posts/1");
        JsonPath jsonPath = new JsonPath(response.asString());
        String author = jsonPath.get("author");

        Assert.assertEquals(author, "Tomek");

    }
}
