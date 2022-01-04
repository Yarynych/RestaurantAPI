package ua.yarynych.demoapi.repository;

import org.springframework.data.repository.CrudRepository;
import ua.yarynych.demoapi.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
