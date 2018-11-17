package com.dicegame;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static String roundDoubleToString(double d){

        return  new BigDecimal(Double.toString(d)).setScale(2, RoundingMode.HALF_UP).toString().concat("%");
    }
}
