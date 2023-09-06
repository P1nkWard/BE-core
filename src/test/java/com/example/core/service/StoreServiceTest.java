package com.example.core.service;

import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.StoreDto;
import com.example.core.member.repository.StoreRepository;
import com.example.core.member.service.StoreDefaultService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    @Mock
    private StoreRepository storeRepository;
    @InjectMocks
    private StoreDefaultService storeService;

    @Nested
    @DisplayName("가게 생성")
    class CreateStore {
        private String masterId;
        private String storeName;

        @BeforeEach
        void setup() {
            masterId = "id";
            storeName = "storeName";
        }

        @Nested
        @DisplayName("정상케이스")
        class SuccessCase {
            @Test
            @DisplayName("새로운 가게 생성")
            void createStoreSuccess() {
                StoreDto store = new StoreDto(masterId, storeName);
                lenient().when(storeRepository.save(any(StoreDto.class))).thenReturn(store.toEntity()); // 영속화

                StoreDto result = storeService.createStore(masterId, storeName);

                Assertions.assertThat(store.getMasterId()).isEqualTo("id");
                Assertions.assertThat(store.getStoreName()).isEqualTo("storeName");

            }

        }

        @Nested
        @DisplayName("비정상케이스(Null인경우)")
        class FailCase {
            @Test
            @DisplayName("가게 생성 실패")
            void createStoreFail() {
                lenient().when(storeRepository.save(any(StoreDto.class))).thenReturn(null);
                StoreDto result = storeService.createStore(masterId, storeName);

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


            }

            @Nested
            @DisplayName("수정 실패")
            class FailedUpdate {

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
