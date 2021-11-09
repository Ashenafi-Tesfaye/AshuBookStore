package ashubookstore.withspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ashubookstore.withspring.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	public Address findFirstByUserIdOrderByDateAddedDesc(Long userId);
}
