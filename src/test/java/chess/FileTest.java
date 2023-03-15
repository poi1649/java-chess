package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @Nested
    class 생성 {
        @ParameterizedTest
        @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8"})
        void should_정상생성_when_1에서8이_입력되었을때(final String input) {
            //given

            //when

            //then
            assertThat(File.from(input)).isInstanceOf(File.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "0", "9", "5.5", "SDdsf", "오"})
        void should_예외를던진다_when_1에서8외의값이_입력됐을때(final String input) {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> File.from(input);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("File은 1에서 8사이의 값 이어야 합니다.");
        }
    }

    @Nested
    class 거리계산 {
        @Test
        void should_거리를_반환한다_when_다른file을받았을때() {
            //given
            File criteria = File.FIVE;
            File compare = File.FOUR;
            int expected = 1;

            //when
            int actual = criteria.calculateDistance(compare);

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}