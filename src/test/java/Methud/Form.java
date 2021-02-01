package Methud;

import static com.codeborne.selenide.Selenide.$;

public class Form {


    public Form setValueInInputFieldForID(String idFiled, String value) {
        String inputField = "(#%s)";
        String selector = String.format(inputField, idFiled);
        $(selector).setValue(value);
        return this;


        // честно, нет времени пилить остальные методы.

    }
}