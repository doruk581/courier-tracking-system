package com.migros.couriertrackingsystem.infrastructure.repository;

import com.migros.couriertrackingsystem.domain.Courier;
import com.migros.couriertrackingsystem.domain.CourierNotExistException;
import com.migros.couriertrackingsystem.domain.CourierTrackingRepository;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Slf4j
public class MongoCourierTrackingRepository implements CourierTrackingRepository {

    private final com.mongodb.client.MongoClient mongoClient;

    private final MongoCollection<Courier> mongoCollection;

    public MongoCourierTrackingRepository(
            MongoConfiguration configuration,
            com.mongodb.client.MongoClient mongoClient) {
        this.mongoClient = mongoClient;

        final String mongoDbName = configuration.getMongoDbName();
        final String mongoCollectionName = configuration.getMongoCollectionName();


        final CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        final MongoDatabase mongoDatabase = this.mongoClient.getDatabase(mongoDbName).withCodecRegistry(pojoCodecRegistry);
        mongoCollection = mongoDatabase.getCollection(mongoCollectionName, Courier.class);
    }

    @Override
    public void save(final Courier courier) {
        mongoCollection.insertOne(courier);
    }

    @Override
    public Optional<LocalDateTime> findLastEntrance(final String storeName, final String courierName) {
        final Bson filterStoreName = Filters.eq("storeName", storeName);
        final Bson filterCourier = Filters.eq("name", courierName);
        final Bson filters = Filters.and(filterCourier, filterStoreName);
        final Bson sortEntranceTime = Sorts.descending("storeEntranceTime");

        final List<Courier> courierList = mongoCollection.
                find(filters)
                .sort(sortEntranceTime)
                .limit(1)
                .into(new ArrayList<>());

        return courierList != null && !courierList.isEmpty() ? Optional.ofNullable(courierList.get(0).getStoreEntranceTime()) : Optional.empty();
    }

    @Override
    public LinkedList<Courier> findCourierLocations(final String courier) {
        final Bson filter = Filters.eq("name", courier);
        final Bson sort = Sorts.ascending("time");


        final LinkedList<Courier> couriers = mongoCollection
                .find(filter)
                .sort(sort)
                .into(new LinkedList<>());

        if (couriers.isEmpty())
            throw CourierNotExistException.create(courier);


        return couriers;
    }
}
