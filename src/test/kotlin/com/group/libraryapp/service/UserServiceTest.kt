package com.group.libraryapp.service

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
) {

    @AfterEach
    fun clean(){
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장이 정상 동작한다")
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

    @Test
    @DisplayName("유저 조회가 정상 동작한다")
    fun getUsersTest(){
        //given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", null),
        ))
        //when
        val results = userService.getUsers()
        //then
        Assertions.assertThat(results).hasSize(2)
        Assertions.assertThat(results).extracting("name").containsExactlyInAnyOrder("A","B")
        Assertions.assertThat(results).extracting("age").containsExactlyInAnyOrder(20, null)
    }

    @Test
    @DisplayName("유저 업데이트가 정상 동작한다")
    fun updateUserNameTest(){
        //given
        val savedUser = userRepository.save(User("A", null))
        val request = UserUpdateRequest(savedUser.id, "B")
        userService.updateUserName(request)

        val result = userRepository.findAll()[0]
        Assertions.assertThat(result.name).isEqualTo("B")
        //when
        //then
    }

    @Test
    @DisplayName("유저 삭제가 정상 동작한다")
    fun deleteUserTest(){
        //given
        userRepository.save(User("A", null))
        //when
        userService.deleteUser("A")
        //then

        Assertions.assertThat(userRepository.findAll()).isEmpty()
    }


}