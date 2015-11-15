package followTheChampions.dao;

import followTheChampions.models.Competition;
import followTheChampions.models.RegisteredDevice;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Malar on 2015-11-15.
 */
@Transactional
public interface RegisteredDeviceRepository extends BasicJpaRepository<RegisteredDevice, Long> {

    Competition getById(Long id);
}
