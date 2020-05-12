package com.youngjun.book.springboot.web.dto;


import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
/*Junit 기본 AssertThat이 아니라 assertj의 assertThat을 사용하는 이유는 추가적으로 라이브러리가 필요하지 않으며,
*자동완성이 확실하게 지원되기 때문이다.
*/
public class HelloResponseDTOTest {


    @Test
    public void lombokTest(){
        String name ="test";
        int amount = 1000;

        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }//lombokTest()


}//HelloResponseDTOTest
