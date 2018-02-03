package com.example.justforfood.justforfood;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by 972791 on 5/9/2016.
 */
public class RealmInstanceGenerator {
    static Realm realm;
    public static Realm getInstance(Context context){
        RealmConfiguration config = new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        return realm;
    }
}
