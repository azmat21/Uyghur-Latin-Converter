package com.azim.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class is used to convert between the
 *
 * Created by ezmet on 11/10/15.
 */
public class UyghurUlyConverter {

    /**
     * statics
     */
    private static UyghurUlyConverter SINGLE=null;
    private static Map<String,String> ALPHABET_MAP;
    private static String[] VOLWELS = {"a","e","i","é","o","u","ö","ü"};
    private static String HEMZE = "ئ";

    /**
     * Singleton Model
     * @return
     */
    public static UyghurUlyConverter getInstance(){
        if (SINGLE == null){
            SINGLE = new UyghurUlyConverter();
        }
        return SINGLE;
    }

    /**
     * private Constructors
     */
    private UyghurUlyConverter(){
        initMap();
    }

    /**
     * init the Alphabet Map
     */
    private void initMap(){
        ALPHABET_MAP = new HashMap<>();
        ALPHABET_MAP.put("ا","a");
        ALPHABET_MAP.put("ە","e");
        ALPHABET_MAP.put("ى","i");
        ALPHABET_MAP.put("ې","é");
        ALPHABET_MAP.put("و","o");
        ALPHABET_MAP.put("ۇ","u");
        ALPHABET_MAP.put("ۆ","ö");
        ALPHABET_MAP.put("ۈ","ü");
        ALPHABET_MAP.put("ب","b");
        ALPHABET_MAP.put("پ","p");
        ALPHABET_MAP.put("ت","t");
        ALPHABET_MAP.put("ژ","zh");
        ALPHABET_MAP.put("چ","ch");
        ALPHABET_MAP.put("خ","x");
        ALPHABET_MAP.put("د","d");
        ALPHABET_MAP.put("ر","r");
        ALPHABET_MAP.put("ز","z");
        ALPHABET_MAP.put("ج","j");
        ALPHABET_MAP.put("س","s");
        ALPHABET_MAP.put("ش","sh");
        ALPHABET_MAP.put("ف","f");
        ALPHABET_MAP.put("غ","gh");
        ALPHABET_MAP.put("ق","q");
        ALPHABET_MAP.put("ك","k");
        ALPHABET_MAP.put("گ","g");
        ALPHABET_MAP.put("ڭ","ng");
        ALPHABET_MAP.put("ل","l");
        ALPHABET_MAP.put("م","m");
        ALPHABET_MAP.put("ن","n");
        ALPHABET_MAP.put("ھ","h");
        ALPHABET_MAP.put("ي","y");
        ALPHABET_MAP.put("ۋ","w");
        ALPHABET_MAP.put("ئ","'");
        ALPHABET_MAP.put(" ", " ");
    }

    /**
     * Convert Arabic Script Uyghur(ASU) to Latin Script Uyghur(LSU)
     * @param source
     * @return
     */
    public String convertUyghur2Uly(String source){
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        if (source.substring(0,1).equals(HEMZE)){
            i = 1;
        }
        for (;i<source.length();i++){
            String sub = source.substring(i, i + 1);
            stringBuilder.append(getValues(sub));
        }
        return stringBuilder.toString();
    }

    /**
     * Convert Latin Script Uyghur(LSU) to Arabic Script Uyghur(ASU)
     * @param source
     * @return
     */
    public String convertUly2Uyghur(String source){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<source.length(); i++){
            String sub = source.substring(i, i + 1);
            String key = sub;
            if (i == 0 && isVowel(sub)){
                stringBuilder.append(HEMZE);
            }
            if ((sub.equals("z") || sub.equals("c") || sub.equals("s") || sub.equals("g")) && i < source.length()-1){
                String ssub = source.substring(i+1,i+2);
                if (ssub.equals("h")){
                    key = sub + ssub;
                    i++;
                }
            }else if(sub.equals("n") && i < source.length()-1){
                String ssub = source.substring(i+1,i+2);
                if (ssub.equals("g")){
                    key = sub + ssub;
                    i++;
                }
            }else if (sub.equals(" ")) {
                key = sub;
            }
            stringBuilder.append(getKeys(key));
        }
        return stringBuilder.toString();
    }

    /**
     * Return the Key for the Value
     * @param value
     * @return
     */
    private String getKeys(String value){
        for(Map.Entry entry : ALPHABET_MAP.entrySet()){
            if (value.equals(entry.getValue())){
                return entry.getKey().toString();
            }
        }
        return "";
    }

    /**
     * Return the Value for Key, if key is not exist return original key
     * @param key
     * @return
     */
    private String getValues(String key){
        if (ALPHABET_MAP.get(key) != null){
            return ALPHABET_MAP.get(key);
        }

        return key;
    }

    /**
     * is Char Vowels
     * @param str
     * @return
     */
    private boolean isVowel(String str){
        for (int i=0; i<VOLWELS.length; i++){
            if(VOLWELS[i].equals(str)){
                return true;
            }
        }
        return false;
    }

}
