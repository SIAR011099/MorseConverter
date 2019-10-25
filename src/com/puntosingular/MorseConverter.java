package com.puntosingular;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MorseConverter {
    private static Map<Character, String> dictionary = new HashMap<Character, String>() {
        {
            put('A', ".-");
            put('B', "-...");
            put('C', "-.-.");
            put('D', "-..");
            put('E', ".");
            put('F', "..-.");
            put('G', "--.");
            put('H', "....");
            put('I', "..");
            put('J', ".---");
            put('K', "-.-");
            put('L', ".-..");
            put('M', "--");
            put('N', "-.");
            put('O', "---");
            put('P', ".--.");
            put('Q', "--.-");
            put('R', ".-.");
            put('S', "...");
            put('T', "-");
            put('U', "..-");
            put('V', "...-");
            put('W', ".--");
            put('X', "-..-");
            put('Y', "-.--");
            put('Z', "--..");

            put('1', ".----");
            put('2', "..---");
            put('3', "...--");
            put('4', "....-");
            put('5', ".....");
            put('6', "-....");
            put('7', "--...");
            put('8', "---..");
            put('9', "----.");
            put('0', "-----");
        }
    };

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int option = 0;
        while (option != 3) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Convertidor de código Morse");
            System.out.println();
            System.out.println("Introduce el número de la operación que deseas realizar, utilizando el siguiente menú:");
            System.out.println("\t1) Convertir a Morse.");
            System.out.println("\t2) Convertir desde Morse.");
            System.out.println("\t3) Salir.");
            System.out.println();
            System.out.print("\tOpción: ");

            try {
                option = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException nfe) {
                System.out.println();
                System.out.println("El dato introducido no es válido.");
            }

            System.out.println();
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println();

            String userInput = null;
            try {
                switch (option) {
                    case 1:
                        System.out.println("Convertir de texto a Morse");
                        System.out.print("Texto: ");
                        userInput = reader.readLine();

                        String morse = getMorseFromText(userInput);
                        System.out.println("El texto introducido convertido en código Morse es:");
                        System.out.println(morse);
                        break;
                    case 2:
                        System.out.println("Convertir de Morse a texto.");
                        System.out.print("Morse: ");
                        userInput = reader.readLine();

                        String text = getTextFromMorse(userInput);
                        System.out.println("El código Morse introducido convertido en texto es:");
                        System.out.println(text);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println();
            System.out.println();
        }

        System.out.println();
        System.out.println("¡Hasta luego!");
    }

    private static String getMorseFromText(String text) {
        text = text.toUpperCase();
        StringBuilder builder = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                builder.append("   ");
            } else {
                builder.append(dictionary.get(c));
                builder.append(" ");
            }
        }

        String result = builder.toString();
        result = result.trim();
        result = result.replaceAll("    ", "   ");

        return result;
    }

    private static String getTextFromMorse(String morse) {
        StringBuilder builder = new StringBuilder();
        String[] morseWords = morse.split("   ");

        for (String morseWord : morseWords) {
            builder.append(getTextFromMorseWord(morseWord));
            builder.append(" ");
        }

        return builder.toString().trim();
    }

    private static String getTextFromMorseWord(String morseWord) {
        Map<String, Character> morseDictionary = getMorseDictionary();
        StringBuilder builder = new StringBuilder();

        String[] morseChars = morseWord.split(" ");
        for (String morseChar : morseChars) {
            builder.append(morseDictionary.get(morseChar));
        }

        return builder.toString();
    }

    private static Map<String, Character> getMorseDictionary() {
        HashMap<String, Character> morseDictionary = new HashMap<>();
        for (Map.Entry<Character, String> entry : dictionary.entrySet()) {
            morseDictionary.put(entry.getValue(), entry.getKey());
        }
        return morseDictionary;
    }
}
