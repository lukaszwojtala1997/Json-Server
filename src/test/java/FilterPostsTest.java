import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FilterPostsTest extends BaseTest{

    @Test
    public void filterPosts() {
        ExtentTest test = extentReports.createTest("Filter posts");

        given()
                .spec(requestSpecification)
                .queryParam("author", "Tomek").
        when()
                .get().
        then().
                spec(responseSpecification);
    }

    @Test
    public void filterPostsById() {
        ExtentTest test = extentReports.createTest("Filter posts by id");

        given().
                spec(requestSpecification).
                queryParam("id", "1", "3").
        when().
                get().
        then().
                spec(responseSpecification);
    }


    @Test
    public void filterPostsByAuthorAndTitle() {
        ExtentTest test = extentReports.createTest("Filter posts by author and title");

        Map<String, Object> params = new HashMap<>();
        params.put("author", "Tomek");
        params.put("title", "Zaktualizowany tytul");

        given().
                spec(requestSpecification).
                queryParams(params).
        when().
                get()
        .then().
                spec(responseSpecification);
    }
}
