import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePostTest extends BaseTest{

    @Test
    public void deletePost(){
        given().
                pathParam("postId", 21).
                spec(requestSpecification).
        when().
                delete("{postId}").
        then().spec(responseSpecification);
    }
}
