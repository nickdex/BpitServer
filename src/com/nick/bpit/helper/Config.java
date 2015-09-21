/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nick.bpit.helper;

/**
 *
 * @author nick
 */
public interface Config {
    String ADMIN_EMAIL = "nikwarke@gmail.com";
    String REGID_SEPARATOR_KEY = "<!@!>";
    String MSG_SEPARATOR_KEY = "<!@!>";
    
    //Project Details
    String PROJET_NUMBER = "662517051362";
    String SERVER_KEY = "AIzaSyCyp7NzNVb87W7rpsqhmvFiUBukb9H54_k";
    
    //Payload Keys
    String ACTION = "ACTION";
    String EMAIL = "EMAIL";
    String TOKEN = "TOKEN";
    String MESSAGE = "BODY";
    String TIMESTAMP = "TIMESTAMP";
    String NEW_MEMBER = "NEW_MEM";
    //Notification
    String ICON = "small_icon";
    
    String TRUE = "T";
    String FALSE = "F";
    
    //Debug Keys
    String DEBUG = "DEBUG";
    String SHOW_DB_MSGS = "SHOWDBMSGS";
    String SHOW_DB_MEMBERS = "SHOWDBMEMS";
    
    //String SHOW_MAP;
    
}
