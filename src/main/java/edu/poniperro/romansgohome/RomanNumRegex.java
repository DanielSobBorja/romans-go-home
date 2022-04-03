package edu.poniperro.romansgohome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanNumRegex {

    private final Map<String, String> regexCollection = new HashMap<String, String>();

    public RomanNumRegex() {
        //this.addRegex("allRegex", "^M{0,3}(CM|CD|D?C{0,3})?(XC|XL|L?X{0,3})?(IX|IV|V?I{0,3})?$"); // solo funciona con números válidos
        this.addRegex("sumGroup", "(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])");
        this.addRegex("subGroup", "(C[DM])|(X[LC])|(I[VX])");
    }

    public String getRegex(String k) {
        return this.regexCollection.get(k);
    }

    public List<String> getAllRegex() {
        return new ArrayList<String>(this.regexCollection.values());
    }

    public void addRegex(String k, String v) {
        this.regexCollection.putIfAbsent(k, v);
    }

}
