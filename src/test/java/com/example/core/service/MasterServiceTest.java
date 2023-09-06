package com.example.core.service;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.StoreDto;
import com.example.core.member.repository.MasterRepository;
import com.example.core.member.service.MasterDefaultService;
import com.example.core.member.service.MasterService;
import com.example.core.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.*;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MasterServiceTest {
    @Mock // MasterRepository 객체가 비어있어도 상관없음
    private MasterRepository masterRepository;

    @InjectMocks // 이 어노테이션은 해당 클래스의 인스턴스를 생성하면서 @Mock으로 정의한 객체를 주입해줍니다
    private MasterDefaultService masterService;

    Phone phone = new Phone("010", "9948", "1901");
    Address address = new Address("대한민국", "동일로", "802호");
    @Test
    @DisplayName("회원가입기능 테스트")
    void masterJoin() {

        MasterDto masterDto = new MasterDto("id","pw","name",phone,address);
        lenient().when(masterRepository.save(any(MasterDto.class))).thenReturn(masterDto.toEntity());

        masterService.join(masterDto);

    }
    @Test
    @DisplayName("회원조회기능 테스트")
    void masterFind() {
        MasterDto masterDto = new MasterDto("id","pw","name",phone,address);
        lenient().when(masterRepository.save(any(MasterDto.class))).thenReturn(masterDto.toEntity());
        masterService.join(masterDto);
        lenient().when(masterRepository.findById("id")).thenReturn(Optional.of(masterDto.toEntity()));

        // when
        MasterDto responseMaster = masterService.find("id");

        // then
        Assertions.assertThat(masterDto).isNotNull();
        Assertions.assertThat(masterDto.getId()).isEqualTo("id");
        Assertions.assertThat(masterDto.getName()).isEqualTo("name");

    }
    @Test
    @DisplayName("로그인기능 테스트")
    void masterLogin(){
        MasterDto masterDto = new MasterDto("id","pw","name",phone,address);

    }
    @Test
    @DisplayName("로그인실패 기능 테스트")
    void masterLoginFailed(){

    }
    @Test
    @DisplayName("가게 조회 기능 테스트") // 가게조회를 사장님만 할수있어야 한다고 생각해서
    void masterCheckStore(){

    }
    @Test
    @DisplayName("가게 조회 기능 실패 테스트")
    void masterCheckStoreFailed(){

    }
}