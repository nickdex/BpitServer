/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nick.bpit.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author nick
 */
public class TimeStamp {

    public static String getTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss", Locale.ENGLISH);
        return dateFormat.format(new Date());
    }
}
