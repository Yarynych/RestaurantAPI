package ua.yarynych.demoapi.repository;

import org.springframework.data.repository.CrudRepository;
import ua.yarynych.demoapi.model.Bar;

public interface BarRepository extends CrudRepository<Bar, Long> {
}
