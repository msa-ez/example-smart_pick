package smartpick;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PickRepository extends PagingAndSortingRepository<Pick, Long>{

    Pick findByOrderId(Long orderId);
}