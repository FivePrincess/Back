package com.example.guzip.domain.guesthouse;

import com.example.guzip.domain.employee.EmployeeRepository;
import com.example.guzip.domain.employer.EmployerRepository;
import com.example.guzip.domain.likedGuesthouse.LikedGuesthouseRepository;
import com.example.guzip.domain.user.UserRepository;
import com.example.guzip.dto.GuesthouseRequestDto;
import com.example.guzip.dto.GuesthouseResponseDto;
import com.example.guzip.entity.guesthouse.GuestHouse;
import com.example.guzip.entity.liked.LikedGuestHouse;
import com.example.guzip.entity.user.Employee;
import com.example.guzip.entity.user.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuesthouseServiceImpl implements GuesthouseSerivce{

    @Autowired
    private GuesthouseRepository guesthouseRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikedGuesthouseRepository likedGuesthouseRepository;


    public GuestHouse registerGuestHouse(Long employerId, GuesthouseRequestDto guesthouseRequest) {

        //Employer 조회
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new IllegalArgumentException("employer not found"));

        GuestHouse guesthouse = new GuestHouse();

        guesthouse.setGuesthouseName(guesthouseRequest.guesthouseName());
        guesthouse.setLocation(guesthouseRequest.location());
        guesthouse.setContact(guesthouseRequest.contact());
        guesthouse.setIntro(guesthouseRequest.intro());
        guesthouse.setAverageRating(guesthouseRequest.averageRating());
        guesthouse.setEmployer(employer); // 연관된 Employer 설정
        //이미지랑 해시태크 추가 구현은 따로 해야함

        // Guesthouse 저장
        return guesthouseRepository.save(guesthouse);
    }

    public List<GuesthouseResponseDto> getAllGuesthouses(Long user_id) {
        List<GuestHouse> allGuestHouse = guesthouseRepository.findAll();
        List<GuesthouseResponseDto> guesthouseResponseDtos = new ArrayList<>();
        guesthouseResponseDtos = toDtoList(allGuestHouse, user_id);
        return guesthouseResponseDtos;
    }

    public List<GuesthouseResponseDto> getGuesthouseByAverageRating(Long user_id){
        List<GuestHouse> allGuestHouse = guesthouseRepository.findAllByOrderByAverageRatingDesc();
        List<GuesthouseResponseDto> guesthouseResponseDtos = new ArrayList<>();
        guesthouseResponseDtos = toDtoList(allGuestHouse, user_id);
        return guesthouseResponseDtos;
    };

    public List<GuesthouseResponseDto> getGuesthouseByRatingCount(Long user_id){
        List<GuestHouse> allGuestHouse = guesthouseRepository.findAllByOrderByRatingsCountDesc();
        List<GuesthouseResponseDto> guesthouseResponseDtos = new ArrayList<>();
        guesthouseResponseDtos = toDtoList(allGuestHouse, user_id);
        return guesthouseResponseDtos;
    };

    public String likeGuesthouse (Long guesthouse_id, Long user_id){

        Employee employee = employeeRepository.findByUserUserId(user_id);
        Optional<LikedGuestHouse> userFavoriteFacility = likedGuesthouseRepository.findByEmployeeEmployeeId(employee.getEmployeeId());
        String result;

        if(userFavoriteFacility.isPresent()){
            likedGuesthouseRepository.deleteByGuestHouseGuesthouseIdAndEmployeeEmployeeId(guesthouse_id, employee.getEmployeeId());
            result = "좋아요 취소 완료";
        }
        else {
            LikedGuestHouse likedGuesthouse1 = new LikedGuestHouse();
            GuestHouse guestHouse = guesthouseRepository.findByGuesthouseId(guesthouse_id);
            likedGuesthouse1.setGuestHouse(guestHouse);
            likedGuesthouse1.setEmployee(employee);
            likedGuesthouseRepository.save(likedGuesthouse1);
            result = "좋아요 추가 완료";
        }
        return result;
    }

    public static GuesthouseResponseDto toDto(GuestHouse guestHouse, boolean isLiked) {
        return new GuesthouseResponseDto(
                guestHouse.getGuesthouseId(),
                guestHouse.getGuesthouseName(),
                guestHouse.getLocation(),
                guestHouse.getAverageRating(),
                guestHouse.getRatingsCount(),
                isLiked ? 1 : 0 // 좋아요 여부에 따라 1 또는 0 설정
        );
    }

    public List<GuesthouseResponseDto> toDtoList(List<GuestHouse> guestHouses, Long userId) {
        Employee employee = employeeRepository.findByUserUserId(userId);

        return guestHouses.stream()
                .map(guestHouse -> {
                    boolean isLiked = likedGuesthouseRepository.findAllByGuestHouseGuesthouseIdAndEmployeeEmployeeId(
                            employee.getEmployeeId(), guestHouse.getGuesthouseId()
                    ).isPresent(); // 좋아요 기록이 있으면 true, 없으면 false
                    return toDto(guestHouse, isLiked);
                })
                .collect(Collectors.toList());
    }
}
