package ashubookstore.withspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ashubookstore.withspring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	
}
