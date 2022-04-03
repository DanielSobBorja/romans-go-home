package edu.poniperro.romansgohome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.assertj.core.api.Assertions.assertThat;

import edu.poniperro.romansgohome.RomanNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RomanNumberTest {

    public RomanNumber romanNumber;

    /**
     * Grupos sumatorios M, C, X, I
     */

    @ParameterizedTest
    @CsvSource({
            "1000,  M",
            "2000,  UMMU",
            "3000,  UMMMU"
    })
    public void grupo_M_test(Short decimal, String roman) {

        //String testCase = "M";
        romanNumber = new RomanNumber(roman);
        assertEquals(decimal, romanNumber.toDecimal());

        /**
         * El caso MMMM es control de errores
         * y no puede estar en el test de la logica
         * Asumimos que la entrada es correcta.
         * Sino, hay que programar la gestion de errores
         */
    }

    @Test
    public void tres_repeticiones_C_test() {

        String testCase = "UMMMUCCCU";
        romanNumber = new RomanNumber(testCase);
        assertEquals(3300, romanNumber.toDecimal());
    }

    @Test
    public void tres_repeticiones_X_test() {

        String testCase = "UMMMUXXXU";
        romanNumber = new RomanNumber(testCase);

        assertEquals(3030, romanNumber.toDecimal());
    }

    @Test
    public void tres_repeticiones_I_test() {

        String testCase = "UMMMUIIIU";
        romanNumber = new RomanNumber(testCase);

        assertEquals(3003, romanNumber.toDecimal());
    }

    @Test
    public void una_D_test() {

        String testCase = "UMMMUDUIIIU";
        romanNumber = new RomanNumber(testCase);
        assertEquals(3503, romanNumber.toDecimal());

        testCase = "MMMUCDUIIIU";
        romanNumber = new RomanNumber(testCase);
        assertNotEquals(3503, romanNumber.toDecimal());
    }

    /**
     * Grupos sustractivos
     * IV(4), IX(9),
     * XL(40), XC(90),
     * CD(400), CM(900)
     */

    @Test
    public void grupo_C_DM_test() {

        String testCase = "UCDU";
        romanNumber = new RomanNumber(testCase);
        assertEquals(400, romanNumber.toDecimal());

        testCase = "UCMU";
        romanNumber = new RomanNumber(testCase);
        assertEquals(900, romanNumber.toDecimal());
    }

    @Test
    public void grupo_X_LC_test() {

        String testCase = "UXLU";
        romanNumber = new RomanNumber(testCase);
        assertEquals(40, romanNumber.toDecimal());

        testCase = "UXCU";
        romanNumber = new RomanNumber(testCase);
        assertEquals(90, romanNumber.toDecimal());
    }

    @Test
    public void grupo_I_VX_test() {

        String testCase = "UIVU";
        romanNumber = new RomanNumber(testCase);
        assertEquals(4, romanNumber.toDecimal());

        testCase = "UIXU";
        romanNumber = new RomanNumber(testCase);
        assertEquals(9, romanNumber.toDecimal());
    }

    @Test
    public void grupos_sumatorios_tres_digitos_test() {
        String testCase = "MMMDCCCLXXXVIII"; // 3888
        romanNumber = new RomanNumber(testCase);
        assertEquals(3888, romanNumber.toDecimal());
    }

    @Test
    public void grupos_sumatorios_test() {
        String testCase = "MMDCCLXXVII"; // 2777
        romanNumber = new RomanNumber(testCase);
        assertEquals(2777, romanNumber.toDecimal());
    }

    @Test
    public void grupos_substractivos_test() {
        String testCase = "CDXLIV"; // 444
        romanNumber = new RomanNumber(testCase);
        assertEquals(444, romanNumber.toDecimal());

        testCase = "CDXXXIX"; // 439
        romanNumber = new RomanNumber(testCase);
        assertEquals(439, romanNumber.toDecimal());
    }

    /**
     * Test de la coleccion de
     * expresiones regulares
     */

    @Test
    public void init_regex_collection_test() {
        String testCase = "V";
        romanNumber = new RomanNumber(testCase);
        assertThat(romanNumber.getRegexCollection().getAllRegex()).hasSize(2);

        // containsExactlyInAnyOrder() soluciona cualquier problema respecto al orden de creación de los regex
        assertThat(romanNumber.getRegexCollection().getAllRegex()).containsExactlyInAnyOrder("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])", "(C[DM])|(X[LC])|(I[VX])");

        assertThat(romanNumber.getRegexCollection().getRegex("sumGroup")).isEqualTo("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])");
        assertThat(romanNumber.getRegexCollection().getRegex("subGroup")).isEqualTo("(C[DM])|(X[LC])|(I[VX])");
    }

    /**
     * Test del tipo enumerado
     * RomanSymbols
     */
    @ParameterizedTest
    @CsvSource({
            "5,   V",
            "4,   IV",
            "900, CM"
    })
    public void valor_decimal_test(Short decimal, String roman) {
        romanNumber = new RomanNumber(roman);
        assertEquals(decimal, romanNumber.decimalValue(roman));
    }
}