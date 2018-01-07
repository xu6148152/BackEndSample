package com.binea.log;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.AppenderSkeleton;

/**
 * Created by binea
 * Date: 5/11/2017
 * TIME: 8:24 PM
 */
public class MongoAppender extends AppenderSkeleton {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<BasicDBObject> logsCollection;

    private String connectionUrl;
    private String databaseName;
    private String collectionName;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
