package com.example.guzip.domain.liked;

import com.example.guzip.domain.employee.EmployeeRepository;
import com.example.guzip.dto.LikedGuesthouseResponseDTO;
import com.example.guzip.entity.liked.LikedGuestHouse;
import com.example.guzip.entity.user.Employee;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikedGuesthouseService {

    private final EmployeeRepository employeeRepository;
    private final LikedGuesthouseRepository likedGuesthouseRepository;

    public LikedGuesthouseService(EmployeeRepository employeeRepository, LikedGuesthouseRepository likedGuesthouseRepository) {
        this.employeeRepository = employeeRepository;
        this.likedGuesthouseRepository = likedGuesthouseRepository;
    }

    public LikedGuesthouseResponseDTO getLikedGuesthouse(Long employeeId) {
        Employee employee = getEmployee(employeeId);

        List<LikedGuestHouse> likedGuestHouses = likedGuesthouseRepository.findByEmployeeOrderByCreatedDateDesc(employee);
        long likedGuestHouseCount = likedGuesthouseRepository.countByEmployee(employee);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<LikedGuesthouseResponseDTO.GuesthouseLikedItemDTO> likedGuesthouseItems = likedGuestHouses.stream()
                .map(likedGuestHouse -> new LikedGuesthouseResponseDTO.GuesthouseLikedItemDTO(
                        likedGuestHouse.getLikeHouseId(),
                        likedGuestHouse.getCreatedDate().format(dateFormatter),
                        likedGuestHouse.getGuestHouse().getGuesthouseName(),
                        likedGuestHouse.getGuestHouse().getLocation(),
                        likedGuestHouse.getGuestHouse().getContact()
                ))
                .collect(Collectors.toList());

        return new LikedGuesthouseResponseDTO(employeeId, likedGuestHouseCount, likedGuesthouseItems);
    }

    private Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("Employee not found"));
    }
}
