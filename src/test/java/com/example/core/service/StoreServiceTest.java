package com.example.core.service;

import com.example.core.member.domain.Store;
import com.example.core.member.dto.StoreDto;
import com.example.core.member.persistence.MasterRepository;
import com.example.core.member.persistence.StoreRepository;
import com.example.core.member.service.StoreDefaultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private MasterRepository masterRepository;
    @InjectMocks
    private StoreDefaultService storeService;

    // 값이 넘어올것이라고 예측해서 작성함

    @BeforeEach
    public void setUp(){

//        Store store = new Store("storeName", new Phone("010", "0000", "0000")
//                new Address("대한민국 경기도 의정부시1", "경민로 00길", "401호"),
//                );

    }


    @Nested
    @DisplayName("가게 생성")
    class CreateStore {
        private String masterId;
        private String storeName;

        @BeforeEach
        void setup() {
            masterId = "masterId";
            storeName = "storeName";
        }

        @Nested
        @DisplayName("정상케이스")
        class SuccessCase {
            @Test
            @DisplayName("새로운 가게 생성")
            void createStoreSuccess() {
                // 모의 객체 설정: 이미 존재하는 가게를 반환하도록 설정
                when(storeRepository.findById(storeName)).thenReturn(Optional.of(new Store()));

                // 테스트 및 검증
                NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
                    storeService.registerStore(masterId, new StoreDto(storeName));
                });

                verify(storeRepository, times(1)).findById(storeName); // findById가 1번 호출되어야 함
                verifyNoMoreInteractions(storeRepository); // 추가적인 상호작용은 없어야 함

            }

        }
        @Nested
        @DisplayName("비정상케이스(Null인경우)")
        class FailCase {
            @Test
            @DisplayName("가게 생성 실패")
            void createStoreFail() {
                // 이미 존재하는 가게를 반환하도록 설정
                when(storeRepository.findById(storeName)).thenReturn(Optional.of(new Store()));

                // 서비스 메서드 호출
                Optional<Store> result = storeService.registerStore(masterId,new StoreDto(storeName));

                // 결과를 검증: Optional은 비어 있어야 함
                assertFalse(result.isPresent(), "이미 존재하는 가게입니다.");

                // save 메서드가 호출되지 않아야 함
                verify(storeRepository, never()).save(any(Store.class));

            }

        }

        @Nested
        @DisplayName("가게 수정")
        class UpdateStore {
            private String masterId;
            private String storeName;

            @Nested
            @DisplayName("수정 성공")
            class SuccessUpdate {
                @Test
                void modifyStoreSuccess(){
                    // 가짜 데이터 생성
                    StoreDto storeDto = new StoreDto();
                    storeDto.setStoreName("UpdatedStoreName");
                    // 가짜 가게 데이터 생성
                    Store fakeStore = new Store();
                    when(storeRepository.save(any(Store.class))).thenReturn(fakeStore);

                    // 서비스 메서드 호출
                    Store modifiedStore = storeService.modifyStore(storeDto);

                    // 결과 검증
                    assertNotNull(modifiedStore, "수정된 가게는 null이 아니어야 함");
                    assertEquals(storeDto.getStoreName(), modifiedStore.getStoreName(), "UpdatedStoreName");

                    // save 메서드가 호출되었는지 검증
                    verify(storeRepository, times(1)).save(any(Store.class));
                }



            }

            @Nested
            @DisplayName("수정 실패")
            class FailedUpdate {
                @Test
                void modifyStoreFailed(){
                    // 가게가 존재하지 않음을 나타내는 Optional 객체를 반환하도록 설정
//                    when(storeRepository.findById(nonExistingStoreName)).thenReturn(Optional.empty());

                    // 서비스 메서드 호출
                    StoreDto storeDto = new StoreDto();
                    storeDto.setStoreName("UpdatedStoreName");
                    Store modifiedStore = storeService.modifyStore(storeDto);

                    // 결과를 검증: 수정된 가게는 null이어야 함
                    assertNull(modifiedStore, "가게 수정 실패 시 결과는 null이어야 함");

                }


            }
        }
    }
//    @Test
//    @DisplayName("가게 만들기 테스트")
//    void createStoreTest(){
//        StoreDto storeDto = new StoreDto("masterId","storeName");
//        lenient().when(storeRepository.save(any(StoreDto.class))).thenReturn(storeDto.toEntity());
//        storeService.join(storeDto);
//    }
//    @Test
//    @DisplayName("아이디검증테스트")
//    void checkMasterIdTest(){
//
//    }
}
