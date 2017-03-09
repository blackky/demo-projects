/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.dao;

import com.sample.bean.Hash;
import com.sample.util.HashUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Shubhendu Shekhar
 */
public class HashDAO extends AbstractDAO {

    private final int MIN_HASH_REQ = 56684;

    private static HashDAO hashDAO;
    
    private HashDAO() {
        super("hash", Hash.class);
    }

    public void populateDB() {
        long current_count = count();
        List<Object> hashList = new ArrayList<>();
        while (current_count < MIN_HASH_REQ) {
            Hash hash = new Hash();
            hash.setHash(new String(Base64.encodeBase64(HashUtil.hash(new String(Base64
                    .encodeBase64(HashUtil.getSalt()))
                    .toCharArray(), HashUtil.getSalt()))));
            hashList.add(hash);
            current_count++;
        }
        if(hashList.size()>0)
        insertMany(hashList);
    }
    
    public static HashDAO getInstance(){
    if(hashDAO==null){
    hashDAO=new HashDAO();
    }
    return hashDAO;
    }

}
