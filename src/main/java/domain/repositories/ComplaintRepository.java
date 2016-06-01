package domain.repositories;

import domain.entities.Complaint;
import org.springframework.data.repository.CrudRepository;

public interface ComplaintRepository extends CrudRepository<Complaint, Integer> {
}
