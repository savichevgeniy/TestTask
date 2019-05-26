package Application.services;

import Application.domain.CatImage;

import java.util.List;

public interface ICatServices {
    List<CatImage> allCats();
    void saveAll(Iterable<CatImage> catImages);
}
