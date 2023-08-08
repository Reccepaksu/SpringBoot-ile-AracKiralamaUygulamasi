package kodlama.io.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.brand;

public interface brandRepository extends JpaRepository<brand,Integer>{

	boolean existByName(String name);
	
}
