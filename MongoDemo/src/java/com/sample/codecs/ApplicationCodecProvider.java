/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.codecs;

import com.sample.bean.Hash;
import com.sample.bean.User;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/**
 *
 * @author Shubhendu Shekhar
 */
public class ApplicationCodecProvider implements CodecProvider  {

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if(clazz==User.class){
        return (Codec<T>) new UserCodec();
        }else if(clazz==Hash.class){
        return (Codec<T>) new HashCodec();
        }
        
        return null;
    }

}
