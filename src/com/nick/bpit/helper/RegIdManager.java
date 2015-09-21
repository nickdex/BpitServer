package com.nick.bpit.helper;

import com.nick.bpit.CcsClient;
import com.nick.bpit.core.CcsMessage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class RegIdManager extends TimeStamp {

    private final String REG_ID_STORE = getClass().getResource("/GCMRegId.txt").getPath();
    private final static RegIdManager instance = new RegIdManager();
    private Map<String, String> regIdMap;

    public static RegIdManager getInstance() {
        return instance;
    }

    public String writeToFile(String email, String token) {
        regIdMap = readFromFile();
//only email(key) and token(value) are available in File. For more, apply DBMS
        if (!regIdMap.containsValue(token)) {
            boolean appendToFile = !regIdMap.containsKey(email);
            String regId;
            if (appendToFile) {
                try (PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(REG_ID_STORE, appendToFile)))) {
                    regId = email + Config.REGID_SEPARATOR_KEY + token;
                    out.println(regId);
                    System.out.println("New User Added");
                } catch (IOException e) {
                    System.out.println("Writing failed\n" + e);
                    return Config.FALSE;
                }
                return Config.TRUE;
            } else {
                regIdMap.replace(email, token);      //Refresh Token
                System.out.println("Token Refreshed");
                try (PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(REG_ID_STORE, appendToFile)))) {
                    for (String key : regIdMap.keySet()) {
                        regId = key + Config.REGID_SEPARATOR_KEY + regIdMap.get(key);
                        out.println(regId);
                    }
                } catch (IOException e) {
                    System.out.println("Writing failed\n" + e);
                }
                return Config.FALSE;
            }
        } else {
            System.out.println("Already Registered");
            return Config.FALSE;
        }
    }

    public Map<String, String> readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(REG_ID_STORE))) {
            String line;
            String[] record;
            regIdMap = new HashMap<>();
            while ((line = br.readLine()) != null) {
                record = line.split(Config.REGID_SEPARATOR_KEY);
                if (record.length > 0) {
                    regIdMap.put(record[0], record[1]);
                } else {
                    regIdMap.put(record[0], null);
                }
            }

            return regIdMap;
        } catch (IOException e) {
            System.out.println("Reading failed.\n" + e);
        }
        return null;
    }

    public String getAdmin() {
        Map<String, String> map = readFromFile();
        return map.get(Config.ADMIN_EMAIL);
    }
}
