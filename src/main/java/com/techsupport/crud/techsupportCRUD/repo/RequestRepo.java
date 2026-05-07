package com.techsupport.crud.techsupportCRUD.repo;

import com.techsupport.crud.techsupportCRUD.model.SupportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<SupportRequest, Long> {
    @Query("SELECT s FROM SupportRequest s WHERE s.name LIKE %?1%")
    List<SupportRequest> search(String keyword);

    @Query("SELECT COUNT(*) FROM SupportRequest")
    long count();

    @Query("SELECT COUNT(s) FROM SupportRequest s WHERE s.status = ?1")
    String countStatus(String status);

    @Query("SELECT COUNT(s) FROM SupportRequest s WHERE s.deviceType = ?1")
    String countDeviceType(String deviceType);

    @Query("SELECT r.deviceType, COUNT(r) FROM SupportRequest r GROUP BY r.deviceType ORDER BY COUNT(r) DESC")
    List<Object[]> findDeviceTypeCount();

    @Query("SELECT COUNT(*) FROM UrgentSupportRequest")
    long countUrgentSupportRequest();

    List<SupportRequest> findByDateCreatedBetween(LocalDateTime start, LocalDateTime end);

    List<SupportRequest> findAllByOrderByDateCreatedAsc();

    SupportRequest findTopByOrderByDateCreatedDesc();
}
