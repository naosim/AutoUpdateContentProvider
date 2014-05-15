package com.naosim.autoupdatecontentprovider.app.contentprovider.database.schame;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujitanao on 2014/05/15.
 */
public class Manifests {
    public static final String TABLE_NAME = "manifests";
    public enum Column {
        _id("integer primary key autoincrement"),
        packagename("text not null"),
        version("integer not null"),
        manifest_url("text not null"),
        content_url("text not null"),
        main_file_name("text"),
        parent_packagename("text"),
        is_update("integer");

        private String type;
        Column(String type) {
            this.type = type;
        }

        public String type() {
            return this.type;
        }
    }

    public static String getCreateTableSql() {
        return getCreateTableSql(TABLE_NAME, Column.values());
    }

    public static String getCreateTableSql(String tableName, Column[] values) {
        List<String> params = new ArrayList<String>();
        for(Column column : values) {
            params.add(column.name() + " " + column.type());
        }
        return String.format("create table %s (%s);", tableName, TextUtils.join(",", params));
    }
}
