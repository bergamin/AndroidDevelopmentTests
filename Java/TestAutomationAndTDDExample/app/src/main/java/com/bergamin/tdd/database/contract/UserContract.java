package com.bergamin.tdd.database.contract;

import android.provider.BaseColumns;

public interface UserContract extends BaseColumns {
    String TABLE_NAME = "users";
    String KEY_NAME = "name";
}
