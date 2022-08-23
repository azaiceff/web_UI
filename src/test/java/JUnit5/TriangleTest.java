package JUnit5;

import JUnit5.MyException.MyException;
import JUnit5.MyException.MyExceptionNotTriangle;
import JUnit5.MyException.MyExceptionSegment;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
@Story("Story")
public class TriangleTest {
    private static final Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @ParameterizedTest
    @CsvFileSource(resources = "/JUnit5/testsFiles/positiveTest.csv", delimiter = ';')
    void positiveTestTriangle(String a, String b, String c, Double expectationResult) throws MyException {
        assumeFalse((a == null) || (b == null) || (c == null) || expectationResult == null);
        double calculationError = 1.0E-4;
        double actualAreaTriangle = Double.parseDouble(Objects.requireNonNull(TriangleArea.areaTriangle(a, b, c)));
        double actualCalculationError = Math.abs(expectationResult - actualAreaTriangle);
        String result = "\nФактический результат - " + actualAreaTriangle + " Ожидалось - " + expectationResult
                + " Большая погрешность вычисления или неверный расчет";
        logger.info("Positive Test" + result);
        Assertions.assertAll(
                () -> assertTrue(calculationError >= actualCalculationError, result),
                () -> assertTrue(Objects.requireNonNull(TriangleArea.areaTriangle(a, b, c)).contains("."), "Не содержит точку!"),
                () -> assertNotNull(TriangleArea.areaTriangle(a, b, c)),
                () -> assertTimeout(Duration.ofMillis(3), () -> TriangleArea.areaTriangle(a, b, c))
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/JUnit5/testsFiles/errorTest.csv", delimiter = ';')
    void calculationErrorTestTriangle(String a, String b, String c, double expectationResult) throws MyException {
        double calculationError = 1.0E-4;
        double actualAreaTriangle = Double.parseDouble(Objects.requireNonNull(TriangleArea.areaTriangle(a, b, c)));
        double actualCalculationError = Math.abs(expectationResult - actualAreaTriangle);
        String result = "Фактический результат - " + actualAreaTriangle + " Ожидалось - " + expectationResult
                + " Большая погрешность вычисления или неверный расчет";
        assertTrue(calculationError <= actualCalculationError, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/JUnit5/testsFiles/textTest.csv", delimiter = ';')
    void textTestTriangle(String a, String b, String c) {
        assertThrows(NumberFormatException.class, () -> TriangleArea.areaTriangle(a, b, c), "нет исключения NumberFormatException");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/JUnit5/testsFiles/IllegalArgumentTest.csv", delimiter = ';')
    void IllegalArgumentTest(String a, String b, String c) {
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.areaTriangle(a, b, c), "нет исключения IllegalArgumentException");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/JUnit5/testsFiles/zeroTest.csv", delimiter = ';')
    void nullTest(String a, String b, String c) throws MyException {
        assertNull(TriangleArea.areaTriangle(a, b, c));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/JUnit5/testsFiles/segmentTest.csv", delimiter = ' ')
    void segmentTest(String a, String b, String c) throws MyException {
        assumeFalse(a.equals("0") || b.equals("0") | c.equals("0"));
        try {
            System.out.println(TriangleArea.areaTriangle(a, b, c));
        } catch (MyExceptionSegment e) {
            logger.error(e.getMessage());
        }
        assertThrows(MyException.class, () -> TriangleArea.areaTriangle(a, b, c), "нет исключения MyExceptionSegment");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/JUnit5/testsFiles/notTriangleTest.csv", delimiter = ';')
    void notTriangleTest(String a, String b, String c) throws MyException {
        try {
            System.out.println(TriangleArea.areaTriangle(a, b, c));
        } catch (MyExceptionNotTriangle e) {
            logger.error(e.getMessage());
        }
        assertThrows(MyException.class, () -> TriangleArea.areaTriangle(a, b, c), "нет исключения MyExceptionNotTriangle");
    }

}
