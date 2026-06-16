package com.StayHub.StayHub.Repository;

import com.StayHub.StayHub.Dto.HotelSearchResponseDto;
import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.Inventory;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findByRoomIdAndDateBetween(
            Long roomId,
            LocalDate startDate,
            LocalDate endDate
    );
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
 SELECT i
        FROM Inventory i
        WHERE i.room.id = :roomId
        AND i.date >= :fromDate
        AND i.date < :toDate
 """)
    List<Inventory> findAndLockInventory(Long roomId, LocalDate fromDate,LocalDate toDate);

    @Query("""
SELECT DISTINCT i.room.hotel
FROM Inventory i
WHERE i.room.hotel.contactDetails.city = :city
AND i.date >= :fromDate
AND i.date < :toDate
AND i.closed = false
AND (i.totalCount - i.bookedCount - i.reservedCount) >= :roomCount
GROUP BY i.room.id,i.room.hotel
HAVING COUNT(i) = :nights
""")
    Page<Hotel> searchHotel(String city , LocalDate fromDate , LocalDate toDate, Integer roomCount, Long nights , Pageable pageable);
}
