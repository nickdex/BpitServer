/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nick.bpit.helper;

import com.nick.bpit.CcsClient;
import com.nick.bpit.core.CcsMessage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nick
 */
public class MessageManager extends TimeStamp {

    final String MSG_FILE_PATH = getClass().getResource("/Messages.txt").getPath();
    private final static MessageManager instance = new MessageManager();
    private static Map<String, String> messageMap = new HashMap<>();

    public static MessageManager getInstance() {
        return instance;
    }

    public void writeMsg(String body, String timestamp) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(MSG_FILE_PATH, true)))) {
            messageMap = readMsg();
   
            writer.println(timestamp + Config.MSG_SEPARATOR_KEY + body);
            
            System.out.println("Message Added");
        } catch (Exception e) {
            System.out.println("Exception Encountered in writing messages : " + e);
        }
    }

    public Map<String, String> readMsg() {
        try (BufferedReader read = new BufferedReader(new FileReader(MSG_FILE_PATH))) {
            String body;     String[] line;
            while ((body = read.readLine()) != null) {
                    line = body.split(Config.MSG_SEPARATOR_KEY);
                    messageMap.put(line[0], line[1]);
            }
            return messageMap;
        } catch (Exception e) {
            System.out.println("Exception Encountered in reading messages : " + e);
        }
        return null;
    }
}
