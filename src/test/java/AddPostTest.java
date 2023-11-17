import com.aventstack.extentreports.ExtentTest;
import io.restassured.path.json.JsonPath;
import model.Post;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class AddPostTest extends BaseTest{

    static String id;

    @Test
    public void addPost(){
        ExtentTest test = extentReports.createTest("Add post");

        String newPost = "{\n" +
                "    \"title\": \"Pierwszy post\",\n" +
                "    \"author\": \"Lukasz\"\n" +
                "}";

        given().
                spec(requestSpecification).
                body(newPost).
        when().
                post().
        then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(Post.class);
    }

    @Test
    public void addPostFromFile(){
        ExtentTest test = extentReports.createTest("Add post from file");

        File newPost = new File("src/test/resources/post.json");

        given().
                spec(requestSpecification).
                body(newPost).
        when().
                post().
        then().
                spec(responseSpecification).
                extract().
                body().
                as(Post.class);
    }

        @Test
    public void addPostMap(){
            ExtentTest test = extentReports.createTest("Add post map");

            Map<String, Object> newPost = new HashMap<>();
            newPost.put("title", "tytul z mapy");
            newPost.put("author", "Daria");

            given()
                    .spec(requestSpecification).body(newPost).
            when()
                    .post().
            then()
                    .spec(responseSpecification)
                    .extract()
                    .body()
                    .as(Post.class);
    }

        @Test
    public void addPostObject(){
            ExtentTest test = extentReports.createTest("Add post object");

            Post newPost = new Post();
            newPost.setAuthor("Autor obiektowy");
            newPost.setTitle("Tytul obiektowy");

            given()
                    .spec(requestSpecification)
                    .body(newPost).
            when()
                    .post()
            .then()
                    .spec(responseSpecification)
                    .extract()
                    .body()
                    .as(Post.class);
    }

    @Test
    public void addPostAndGetIdFromResponse(){
        ExtentTest test = extentReports.createTest("Add post and get id from response");

        String newPost = "{\n" +
                "    \"title\": \"Pierwszy post\",\n" +
                "    \"author\": \"Lukasz\"\n" +
                "}";

        String response =
                given()
                    .spec(requestSpecification)
                    .body(newPost)
                .when()
                    .post()
                .then()
                    .spec(responseSpecification)
                    .extract()
                    .response()
                    .asString();

        JsonPath js = new JsonPath(response);
        id = js.getString("id");
    }
}
