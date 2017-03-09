/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.codecs;

import com.sample.bean.User;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 *
 * @author Shubhendu Shekhar
 */
public class UserCodec implements Codec<User>{

    @Override
    public void encode(BsonWriter writer, User value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("_id", value.getEmail());
        writer.writeString("first_name", value.getFirst_name());
        writer.writeString("last_name", value.getLast_name());
        writer.writeString("role", value.getRole());
        writer.writeString("salt", value.getSalt());
        writer.writeEndDocument();
    }

    @Override
    public Class<User> getEncoderClass() {
        return User.class;
    }

    @Override
    public User decode(BsonReader reader, DecoderContext decoderContext) {
        User user = new User();
        reader.readStartDocument();
        user.setEmail(reader.readString("_id"));
        user.setFirst_name(reader.readString("first_name"));
        user.setLast_name(reader.readString("last_name"));
        user.setRole(reader.readString("role"));
        user.setSalt(reader.readString("salt"));
        reader.readEndDocument();
        return user;
    }

}
