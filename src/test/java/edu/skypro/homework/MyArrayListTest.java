package edu.skypro.homework;

import edu.skypro.homework.exceptions.BadIndexException;
import edu.skypro.homework.exceptions.ElementNotFoundException;
import edu.skypro.homework.exceptions.StorageIsFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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

    @Test
    void removeByElementTest() {
        String[] expected = {"privet"};
        assertThat(out.remove("poka"))
                .isEqualTo("poka")
                .isNotIn(expected);
    }

    @Test
    void removeByElementNegativeTest() {
        assertThatExceptionOfType(ElementNotFoundException.class)
                .isThrownBy(() -> out.remove("ok"));
    }

    @Test
    void removeByIndexTest() {
        String[] expected = {"privet"};
        assertThat(out.remove(1))
                .isEqualTo("poka")
                .isNotIn(expected);
    }

    @Test
    void sizeTest() {
        assertThat(out.size())
                .isEqualTo(2);
    }

    @Test
    void containsTest() {
        assertThat(out.contains("poka"))
                .isTrue();
    }

    @Test
    void containsNegativeTest() {
        assertThat(out.contains("ok"))
                .isFalse();
    }

    @Test
    void indexOfTest() {
        assertThat(out.indexOf("poka"))
                .isEqualTo(1);
    }

    @Test
    void lastIndexOfTest() {
        assertThat(out.lastIndexOf("poka"))
                .isEqualTo(1);
    }

    @Test
    void getTest() {
        assertThat(out.get(1))
                .isEqualTo("poka");
    }

    @Test
    void equalsTest() {
        MyArrayList test1 = new MyArrayList(5);
        test1.add("privet");
        test1.add("poka");

        MyArrayList test2 = new MyArrayList(5);
        test2.add("privet");
        test2.add("ok");

        assertThat(out.equals(test1)).isTrue();
        assertThat(out.equals(test2)).isFalse();
        assertThat(out.equals(out.toArray())).isFalse();
    }

    @Test
    void equalsNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.equals(null));
    }

    @Test
    void isEmptyTest() {
        MyArrayList testList = new MyArrayList();
        assertThat(out.isEmpty()).isFalse();
        assertThat(testList.isEmpty()).isTrue();
    }

    @Test
    void clearTest() {
        out.clear();
        assertThat(out.isEmpty()).isTrue();
    }
}