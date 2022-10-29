package ru.project.test.v2.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TorstendasTost
 * @since 25.10.2022
 */
public interface ConverterToUser<F, T> {
    T convertToUser(F dto);

    F revert(T entity);

    default List<T> convertToUserList(List<F> userDtoList) {
        return userDtoList.stream().map(this::convertToUser).collect(Collectors.toList());
    }

    default List<F> revertToUserDtoList(List<T> userDtoList) {
        return userDtoList.stream().map(this::revert).collect(Collectors.toList());
    }
}
