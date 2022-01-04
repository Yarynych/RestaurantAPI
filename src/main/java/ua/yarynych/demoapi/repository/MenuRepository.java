package ua.yarynych.demoapi.repository;

import org.springframework.data.repository.CrudRepository;
import ua.yarynych.demoapi.model.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {
}
