package com.StayHub.StayHub.Repository;

import com.StayHub.StayHub.Dto.RoomCreateRequest;
import com.StayHub.StayHub.Dto.RoomResponseDto;
import com.StayHub.StayHub.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    Page<Room> findAllByHotelId(Long hotelId , Pageable pageable);

}
