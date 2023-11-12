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
