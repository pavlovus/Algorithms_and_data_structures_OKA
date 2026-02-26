package org.example;

public class OneSymbolChange {
    public boolean isOneSymbolChange(String str1, String str2) {
        //Крайні випадки
        if(str1 == null || str2 == null)  return false;
        if(str1.equals(str2)) return true;
        if (str1.length() != str2.length()) return false;

        //Рахуємо к-сть перестановок, якщо більше за 1, то виходимо з циклу
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) count++;
            if (count > 1) break;
        }

        //Повертаємо результат
        return count <= 1;
    }

    //Тестування: в str1 та str2 вказуємо дві стрічки для тестування, запускаємо main, виводиться true або false
    public static void main(String[] args) {
        String str1 = "cat";
        String str2 = "bat";
        OneSymbolChange oneSymbolChange = new OneSymbolChange();
        System.out.println(oneSymbolChange.isOneSymbolChange(str1, str2));
    }
}