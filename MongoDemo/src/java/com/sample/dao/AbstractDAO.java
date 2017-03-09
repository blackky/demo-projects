/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.sample.db.Database;
import java.util.List;
import org.bson.conversions.Bson;

/**
 *
 * @author Shubhendu Shekhar
 */
public abstract class AbstractDAO {

    private final MongoCollection collection;
    private final Class resultClass;
    
    public AbstractDAO(String collection, Class type) {
        this.collection = Database.getCollection(collection, type);
        this.resultClass=type;
    }
    
    public void insertOne(Object document) {
        collection.insertOne(document);
    }
    
    public void insertMany(List<Object> documents){
    collection.insertMany(documents);
    }
    
    public FindIterable fetch(){
    return collection.find(this.resultClass);
    }
    
    public FindIterable fetch(Bson filter){
    return collection.find(filter, this.resultClass);
    }
    
    public void delete(Bson filter, boolean allMatches){
    if(allMatches){
        collection.deleteMany(filter);
    }else{
        collection.deleteOne(filter);
    }
    }
    
    public long count(){
    return collection.count();
    }
    
    public long count(Bson filter){
    return collection.count(filter);
    }
    
}
