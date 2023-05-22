package edu.skypro.homework;

import edu.skypro.homework.exceptions.BadIndexException;
import edu.skypro.homework.exceptions.StorageIsFullException;
import net.bytebuddy.asm.MemberSubstitution;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.context.annotation.PropertySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList out;

    @BeforeEach
    void beforeEach() {
        out = new MyArrayList();
        out.add("privet");
        out.add("poka");
    }

    //    @AfterEach
//    void AfterEach() {
//
//    }
    @Test
    void addTest() {
        String[] expected = {"hi", "ok"};

        assertThat(out.add("ok")).isIn(expected);
    }

    @Test
    void addNullElementTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.add(null));
    }

    @Test
    void addWhenStorageFullTest() {
        out.add("ok");
        assertThatExceptionOfType(StorageIsFullException.class)
                .isThrownBy(() -> out.add("hi"));
    }

    @Test
    void addWithIndexTest() {
        String[] expected = {"privet", "bye"};
        assertThat(out.add(1, "bye")).isIn(expected);
    }
    public static Stream<Arguments> provideParamsForBadIndexExceptionTest() {
        return Stream.of(
                Arguments.of(-2, "hi"),
                Arguments.of(10, "hi")
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForBadIndexExceptionTest")
    void addWithIndexNegativeTest(int index, String string) {
        assertThatExceptionOfType(BadIndexException.class)
                .isThrownBy(() -> out.add(index, string));
    }
}