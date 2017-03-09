/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sample.bean.Hash;
import com.sample.codecs.ApplicationCodecProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

/**
 *
 * @author Shubhendu Shekhar
 */
public class Database {

    private static Properties props;
    private static Database db;
    private static MongoClient client;
    private static int max_conn_idle;
    private static int min_conn;
    private static int server_port;
    private static String server_url;
    private static String auth_db;
    private static String app_db;
    private static String app_user;
    private static String app_pwd;

    private Database() {
        init();
    }

    private CodecRegistry getApplicationCodecRegistry() {
        return CodecRegistries.fromRegistries(CodecRegistries.fromProviders(new ApplicationCodecProvider()),
                MongoClient.getDefaultCodecRegistry());
    }

    private void init() {
        try {
            props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("./application.properties"));
            max_conn_idle = Integer.parseInt(props.getProperty("mongo.max.connection.idle"));
            min_conn = Integer.parseInt(props.getProperty("mongo.min.connection"));
            server_port = Integer.parseInt(props.getProperty("mongo.port"));
            server_url = props.getProperty("mongo.server");
            auth_db = props.getProperty("mongo.auth.db");
            app_db = props.getProperty("mongo.app.db");
            app_user = props.getProperty("mongo.auth.user");
            app_pwd = props.getProperty("mongo.auth.pwd");

            ServerAddress addr = new ServerAddress(server_url, server_port);
            MongoCredential creds = MongoCredential.createScramSha1Credential(app_user,auth_db, app_pwd.toCharArray());
            
            List<MongoCredential> credList = new ArrayList<>();
            credList.add(creds);
            MongoClientOptions options = new MongoClientOptions.Builder()
                    .codecRegistry(getApplicationCodecRegistry())
                    .minConnectionsPerHost(min_conn)
                    .maxConnectionIdleTime(max_conn_idle)
                    .build();
            client = new MongoClient(addr, credList, options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MongoDatabase getConnection() {
        if (db == null || client==null) {
            db = new Database();
        }
        return client.getDatabase(app_db);
    }
    
    public static MongoCollection getCollection(String name, Class type){
        if (db == null || client==null) {
            db = new Database();
        }
        return client.getDatabase(app_db).getCollection(name, type);
}

    public static void closeConnection(){
    if(client!=null){
    client.close();
    client=null;
    db=null;
    }
    }
}
