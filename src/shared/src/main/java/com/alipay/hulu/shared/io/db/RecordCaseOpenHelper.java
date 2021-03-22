/*
 * Copyright (C) 2015-present, Ant Financial Services Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.hulu.shared.io.db;

import org.greenrobot.greendao.database.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by lezhou.wyl on 2018/1/30.
 */
public class RecordCaseOpenHelper extends DaoMaster.OpenHelper {
    public RecordCaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public RecordCaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        if (oldVersion < 4) {
            DaoMaster.dropAllTables(db, true);
            onCreate(db);
        }
    }
}
