package ua.yarynych.demoapi.repository;

import org.springframework.data.repository.CrudRepository;
import ua.yarynych.demoapi.model.Worker;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
}
