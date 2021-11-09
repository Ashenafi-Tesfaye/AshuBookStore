package ashubookstore.withspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ashubookstore.withspring.model.OrderedBook;

public interface OrderedBookRepository extends JpaRepository<OrderedBook, Long> {
	public List<OrderedBook> findByOrderId(Long orderId);

}
