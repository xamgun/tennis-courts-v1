package com.tenniscourts.guests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    @Query("SELECT g FROM Guest g WHERE UPPER(g.name) LIKE CONCAT('%',UPPER(:guestName),'%')")
    List<Guest> findByNameLikeIgnoreCase(@Param("guestName") String guestName);
}
