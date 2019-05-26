package Application.domain;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CatImageTest {

    private JSONObject jsonCat;


    @Test
    void fromJsonReturnsCatWithPassedUrl() {
        String url = "https://cdn2.thecatapi.com/images/cbh.jpg";
        jsonCat = new JSONObject(String.format("{" +
                "\"url\": \"%s\"" +
                "}", url));
        CatImage catImage = CatImage.fromJson(jsonCat);
        assertEquals(catImage.url, url, "Cat image url isn't equal to passed url");
    }
}