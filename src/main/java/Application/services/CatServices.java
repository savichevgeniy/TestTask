package Application.services;

import Application.domain.CatImage;
import Application.repositories.ICatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("catServices")
public class CatServices implements ICatServices {
    private ICatsRepository catsRepository;

    @Autowired
    public CatServices(ICatsRepository catsRepository) {
        this.catsRepository = catsRepository;
    }

    @Override
    public List<CatImage> allCats() {
        List<CatImage> result = new ArrayList<CatImage>();
        catsRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public void saveAll(Iterable<CatImage> catImages) {
        catsRepository.saveAll(catImages);
    }
}
