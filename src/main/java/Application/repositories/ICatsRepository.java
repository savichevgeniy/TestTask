package Application.repositories;

import Application.domain.CatImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component("catsRepository")
public interface ICatsRepository extends CrudRepository<CatImage, Long> {
}
