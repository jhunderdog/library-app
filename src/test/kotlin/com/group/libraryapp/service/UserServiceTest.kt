package com.group.libraryapp.service

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
) {

    @Test
    fun saveUserTest(){
        //given
        val request = UserCreateRequest("한솔로", null)
        //when
        userService.saveUser(request)
        //then
        val results = userRepository.findAll()
        Assertions.assertThat(results).hasSize(1)
        Assertions.assertThat(results[0].name).isEqualTo("한솔로")
        Assertions.assertThat(results[0].age).isNull()
    }

}