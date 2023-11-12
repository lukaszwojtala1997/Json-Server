import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePostTest extends BaseTest{

    @Test
    public void deletePost(){
        AddPostTest addPostTest = new AddPostTest();
        String getId = addPostTest.id;

        given().
                pathParam("postId", getId).
                spec(requestSpecification).
        when().
                delete("{postId}").
        then().spec(responseSpecification);
    }
}
