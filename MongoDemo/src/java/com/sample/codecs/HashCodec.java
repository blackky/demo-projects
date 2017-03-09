/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.codecs;

import com.sample.bean.Hash;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 *
 * @author Shubhendu Shekhar
 */
public class HashCodec implements Codec<Hash>{

    @Override
    public void encode(BsonWriter writer, Hash value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("_id", value.getHash());
        writer.writeEndDocument();
    }
        

    @Override
    public Class<Hash> getEncoderClass() {
        return Hash.class;
    }

    @Override
    public Hash decode(BsonReader reader, DecoderContext decoderContext) {
    Hash hash = new Hash();
    reader.readStartDocument();
    hash.setHash(reader.readString("_id"));
    reader.readEndDocument();
    return hash;
    }

}
