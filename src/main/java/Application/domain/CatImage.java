package Application.domain;

import org.json.JSONObject;

import javax.persistence.*;

@Entity
public class CatImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column
    public String url;

    @Column
    public String path;

    public static CatImage fromJson(JSONObject jsonCat) {
        CatImage result = new CatImage();
        result.url = jsonCat.getString("url");
        return result;
    }
}
