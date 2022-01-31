package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    private final GuestMapper guestMapper;

    @Transactional
    public GuestDTO addGuest(GuestDTO guestDTO) {
        return guestMapper.map(guestRepository.saveAndFlush(guestMapper.map(guestDTO)));
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public GuestDTO findGuestById(Long guestId) {
        return guestMapper.map(guestRepository.findById(guestId).orElseThrow(() -> {
            throw new EntityNotFoundException("Guest not found.");
        }));
    }

    @Transactional
    public GuestDTO updateGuest(GuestDTO guestDTO) {
        Guest guest = guestRepository.findById(guestDTO.getGuestId()).orElseThrow(() -> {
            throw new EntityNotFoundException("Guest not found.");
        });
        guest.setName(guestDTO.getGuestName());
        return guestMapper.map(guestRepository.saveAndFlush(guest));
    }

    public void deleteById(Long guestId) {
        guestRepository.deleteById(guestId);
    }

    public List<GuestDTO> findGuestByName(String guestName) {
        return guestMapper.map(guestRepository.findByNameLikeIgnoreCase(guestName));
    }
}
