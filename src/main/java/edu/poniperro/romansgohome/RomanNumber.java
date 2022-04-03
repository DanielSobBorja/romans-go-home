package edu.poniperro.romansgohome;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumber {
    private final String romanNum;
    private short decimalNum = 0;

    private RomanNumRegex regexCollection = null;

    public RomanNumber(String numeroRomano) {
        this.romanNum = numeroRomano;
        this.decimalNum = (short) 0;
        regexCollection = new RomanNumRegex();
    }

    // getters / setters

    public short getDecimalNum() {
        return this.decimalNum;
    }

    public RomanNumRegex getRegexCollection() {
        return this.regexCollection;
    }

    // class methods

    private List<String> getRegexFromCollection() {
        return getRegexCollection().getAllRegex();
    }

    public void addRegex(String name, String regex) {
        this.getRegexCollection().addRegex(name, regex);
    }

    public short toDecimal() {
        for (String reg : getRegexFromCollection()) {
            Matcher matcher = createMatcher(reg);

            while (matcher.find()) {
                this.decimalNum += decimalValue(matcher.group());
            }
        }

        return this.getDecimalNum();
    }

    public Matcher createMatcher(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.romanNum);
        return matcher;
    }

    public short decimalValue(String romanNum) {
        RomanSymbols symbol = Enum.valueOf(RomanSymbols.class, String.valueOf(romanNum));
        return (short) symbol.getDecimalValue();
    }

    @Override
    public String toString() {
        return this.romanNum;
    }

}
