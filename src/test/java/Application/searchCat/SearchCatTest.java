package Application.searchCat;

import Application.domain.CatImage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class SearchCatTest {

    ISearchCat searchCat = new SearchCat();

    @Test
    void searchThreeOrLessImages() throws IOException {
        int limit = 3;
        String args = String.format("limt=%d", limit);
        List<CatImage> cats = searchCat.searchImages(args);
        assertTrue(cats.size() <= limit, "Search cat return more than three images");
    }
}