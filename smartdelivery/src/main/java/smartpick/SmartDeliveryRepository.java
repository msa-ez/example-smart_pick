package smartpick;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmartDeliveryRepository extends PagingAndSortingRepository<SmartDelivery, Long>{

    SmartDelivery findByOrderId(Long orderId);
}