/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.manager;

import com.sample.bean.Hash;
import com.sample.bean.User;
import com.sample.dao.HashDAO;
import com.sample.dao.UserDAO;
import com.sample.util.HashUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.Document;

/**
 *
 * @author Shubhendu Shekhar
 */
public class UserManager {

    private final UserDAO userDAO = UserDAO.getInstance();
    private final HashDAO hashDAO = HashDAO.getInstance();
    private static final UserManager MANAGER = new UserManager();

    private UserManager(){} // never to be made public
    
    public boolean doLogin(String email, char[] password) {
        boolean flag = false;
        try {
            byte[] salt;
            if (checkExists(email)) {
                User user = (User) userDAO.fetch(new Document("_id", email)).first();
                salt = Base64.decodeBase64(user.getSalt());
            } else {
                /**
                 * Protection against timing attacks
                 */
                salt = Base64.decodeBase64("000000000000==");
                password = "000000000000000000".toCharArray();
            }
            byte[] hash = HashUtil.hash(password, salt);
            long count = hashDAO.count(new Document("_id", Base64.encodeBase64String(hash)));
            if (count == 1) {
                return true;
            } else if (count > 1) {
                /**
                 * Database integrity lost. Log and do not allow to login.
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean createUser(User user, char[] password) {
        boolean flag = false;
        try {
            byte[] salt;
            if (!checkExists(user.getEmail())) {
                salt = HashUtil.getSalt();
                byte[] hash = HashUtil.hash(password, salt);
                Hash hashOb = new Hash();
                hashOb.setHash(Base64.encodeBase64String(hash));
                hashDAO.insertOne(hashOb);
                user.setSalt(Base64.encodeBase64String(salt));
                userDAO.insertOne(user);
                flag = true;
            } else {
                /**
                 * Protection against timing attacks
                 */
                salt = HashUtil.getSalt();
                HashUtil.hash(password, salt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean checkExists(String email) {
        boolean flag = false;
        try {
            Document filter = new Document("_id", email);
            long count = userDAO.count(filter);
            if (count == 1) {
                return true;
            } else if (count > 1) {
                /**
                 * Database integrity lost. Log and do not allow to login.
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public User getDetails(String email){
    return (User)userDAO.fetch(new Document("_id",email)).first();
    }
    
    public static UserManager getInstance(){
    return MANAGER;
    }
}
