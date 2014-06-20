/*
 * COMSAT
 * Copyright (c) 2013-2014, Parallel Universe Software Co. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package co.paralleluniverse.fibers.mongodb;

import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.SettableFuture;
import com.allanbank.mongodb.MongoCollection;
import com.allanbank.mongodb.MongoDbException;
import com.allanbank.mongodb.bson.Document;
import com.allanbank.mongodb.bson.DocumentAssignable;
import com.allanbank.mongodb.client.Client;
import com.allanbank.mongodb.client.MongoDatabaseImpl;
import java.util.concurrent.Future;

/**
 * @author circlespainter
 */
public class FiberMongoDatabaseImpl extends MongoDatabaseImpl {

    public FiberMongoDatabaseImpl(Client client, String string) {
        super(client, string);
    }

    @Override
    public MongoCollection getCollection(String name) {
        return new FiberMongoCollectionImpl(myClient, this, name);
    }
    
    @Override
    @Suspendable
    public Document runCommand(final DocumentAssignable command) throws MongoDbException {
        Document res = null;
        try {
            res = new FiberMongoCallback<Document>() {
                @Override
                protected void requestAsync() {
                    FiberMongoDatabaseImpl.super.runCommandAsync(this, command);
                }
            }.run();
        } catch (SuspendExecution | InterruptedException ex) {
            throw new AssertionError("Should never happen", ex);
        }
        return res;
    }

    @Override
    @Suspendable
    public Document runCommand(final String command) throws MongoDbException {
        Document res = null;
        try {
            res = new FiberMongoCallback<Document>() {
                @Override
                protected void requestAsync() {
                    FiberMongoDatabaseImpl.super.runCommandAsync(this, command);
                }
            }.run();
        } catch (SuspendExecution | InterruptedException ex) {
            throw new AssertionError("Should never happen", ex);
        }
        return res;
    }

    @Override
    @Suspendable
    public Document runCommand(final String command, final DocumentAssignable options) throws MongoDbException {
        Document res = null;
        try {
            res = new FiberMongoCallback<Document>() {
                @Override
                protected void requestAsync() {
                    FiberMongoDatabaseImpl.super.runCommandAsync(this, command, options);
                }
            }.run();
        } catch (SuspendExecution | InterruptedException ex) {
            throw new AssertionError("Should never happen", ex);
        }
        return res;
    }

    @Override
    @Suspendable
    public Document runCommand(final String commandName, final int commandValue, final DocumentAssignable options) throws MongoDbException {
        Document res = null;
        try {
            res = new FiberMongoCallback<Document>() {
                @Override
                protected void requestAsync() {
                    FiberMongoDatabaseImpl.super.runCommandAsync(this, commandName, commandValue, options);
                }
            }.run();
        } catch (SuspendExecution | InterruptedException ex) {
            throw new AssertionError("Should never happen", ex);
        }
        return res;
    }

    @Override
    @Suspendable
    public Document runCommand(final String commandName, final String commandValue, final DocumentAssignable options) throws MongoDbException {
        Document res = null;
        try {
            res = new FiberMongoCallback<Document>() {
                @Override
                protected void requestAsync() {
                    FiberMongoDatabaseImpl.super.runCommandAsync(this, commandName, commandValue, options);
                }
            }.run();
        } catch (SuspendExecution | InterruptedException ex) {
            throw new AssertionError("Should never happen", ex);
        }
        return res;
    }


    // Async w/fiber-blocking future API
    
    @Override
    public Future<Document> runCommandAsync(DocumentAssignable command) {
        final SettableFuture<Document> future = new SettableFuture<>();
        super.runCommandAsync(FiberMongoUtils.callbackSettingFuture(future), command);
        return future;
    }

    @Override
    public Future<Document> runCommandAsync(String command) {
        final SettableFuture<Document> future = new SettableFuture<>();
        super.runCommandAsync(FiberMongoUtils.callbackSettingFuture(future), command);
        return future;
    }

    @Override
    public Future<Document> runCommandAsync(String command, DocumentAssignable options) {
        final SettableFuture<Document> future = new SettableFuture<>();
        super.runCommandAsync(FiberMongoUtils.callbackSettingFuture(future), command, options);
        return future;
    }

    @Override
    public Future<Document> runCommandAsync(String commandName, int commandValue, DocumentAssignable options) {
        final SettableFuture<Document> future = new SettableFuture<>();
        super.runCommandAsync(FiberMongoUtils.callbackSettingFuture(future), commandName, commandValue, options);
        return future;
    }

    @Override
    public Future<Document> runCommandAsync(String commandName, String commandValue, DocumentAssignable options) {
        final SettableFuture<Document> future = new SettableFuture<>();
        super.runCommandAsync(FiberMongoUtils.callbackSettingFuture(future), commandName, commandValue, options);
        return future;
    }
}