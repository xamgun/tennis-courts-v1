package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guest")
@AllArgsConstructor
public class GuestController extends BaseRestController {
    private final GuestService guestService;

    private final GuestMapper guestMapper;

    @ApiOperation(value = "create")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Created", response = GuestDTO.class)})
    @PostMapping
    public ResponseEntity<Void> addGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guestDTO).getGuestId())).build();
    }

    @ApiOperation(value = "find By Id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = GuestDTO.class)})
    @GetMapping(value = "/{guestId}")
    public ResponseEntity<GuestDTO> findGuestById(@PathVariable Long guestId) {
        return ResponseEntity.ok(guestService.findGuestById(guestId));
    }

    @ApiOperation(value = "find By Name")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = GuestDTO.class, responseContainer = "List")})
    @GetMapping(value = "/findByName/{guestName}")
    public ResponseEntity<List<GuestDTO>> findGuestByName(@PathVariable String guestName) {
        return ResponseEntity.ok(guestService.findGuestByName(guestName));
    }

    @ApiOperation(value = "list all")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = GuestDTO.class, responseContainer = "List")})
    @GetMapping
    public ResponseEntity<List<GuestDTO>> findAll() {
        return ResponseEntity.ok(guestMapper.map(guestService.findAll()));
    }

    @ApiOperation(value = "update")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Created", response = GuestDTO.class)})
    @PutMapping
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(guestDTO));
    }

    @ApiOperation(value = "delete")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success")})
    @DeleteMapping(value = "/{guestId}")
    public ResponseEntity<Long> deleteById(@PathVariable Long guestId) {
        guestService.deleteById(guestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
