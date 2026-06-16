package com.StayHub.StayHub.Repository;

import com.StayHub.StayHub.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest,Long> {

    List<Guest> findAllGuestsByUserId(Long userId);
}
