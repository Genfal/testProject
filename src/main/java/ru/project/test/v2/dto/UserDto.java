package ru.project.test.v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author TorstendasTost
 * @since 25.10.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    Long id;

    String name;

    Integer age;

    String country;

    List<String> email;
}
