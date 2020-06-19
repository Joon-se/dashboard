package energyservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DashboardRepository extends CrudRepository<Dashboard, Long> {

    List<Dashboard> findByReservationid(Long reservationid);

        void deleteByReservationid(Long reservationid);
}