package com.StayHub.StayHub.Repository;

import com.StayHub.StayHub.entity.Hotel;
import com.StayHub.StayHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByOwner(User user);
}
