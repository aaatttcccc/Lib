package com.lib.datalist;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数据库查询出的cursor转换成dataList
 */
public class DataList extends ArrayList<DataMap> implements List<DataMap> {

    private static final long serialVersionUID = 1L;

    private String dataListName = null;

    public DataList() {
        this("name1");
    }

    public DataList(String webListName) {
        this.dataListName = webListName;
    }

    public String getDataListName() {
        return dataListName;
    }

    public void setDataListName(String webListName) {
        this.dataListName = webListName;
    }

    /* *
     * cursor转换成dataList
     * @param cursor
     * @param mIDataListLoop 接口，用于循环中对数据做处理
     * @return
     */
    public static DataList CursorToDataList(Cursor cursor, DataListLoop mIDataListLoop) {
        DataList list = new DataList();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (mIDataListLoop != null) {
                    DataMap dataMap = mIDataListLoop.onLoop(handleRow(cursor));
                    list.add(dataMap);
                } else {
                    list.add(handleRow(cursor));
                }
            }
            cursor.close();
        }
        list.setDataListName("tablename");
        return list;
    }

    /* *
     * cursor转换成dataList
     * @param cursor
     * @return
     */
    public static DataList CursorToDataList(Cursor cursor) {
        DataList list = new DataList();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(handleRow(cursor));
            }
            list.setDataListName("tablename");
            cursor.close();
        }
        return list;
    }

    public interface DataListLoop {
        /**
         * 循环事件
         */
        public DataMap onLoop(DataMap dataMap);
    }

    private static DataMap handleRow(Cursor cursor) {
        DataMap map = new DataMap();
        String[] s = cursor.getColumnNames();
        for (int i = 0; i < s.length; i++) {
            map.put(s[i], getObject(cursor, i));
        }
        return map;
    }

    private static Object getObject(Cursor cursor, int i) {
        Object obj = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            int typeInt = cursor.getType(i);
            switch (typeInt) {
                case Cursor.FIELD_TYPE_BLOB:
                    obj = cursor.getBlob(i);
                    break;
                case Cursor.FIELD_TYPE_FLOAT:
                    obj = cursor.getDouble(i);
                    break;
                case Cursor.FIELD_TYPE_INTEGER:
                    obj = cursor.getLong(i);
                    break;
                case Cursor.FIELD_TYPE_NULL:
                    obj = null;
                    break;
                case Cursor.FIELD_TYPE_STRING:
                    obj = cursor.getString(i);
                    break;
                default:
                    break;
            }
        } else {
            if (!cursor.isNull(i)) {
                try {
                    obj = cursor.getString(i);
                } catch (Exception es) {
                    try {
                        obj = cursor.getBlob(i);
                    } catch (Exception eb) {
                        throw new SQLiteException("cursor type is erroy ");
                    }
                }
            }
        }
        return obj;
    }


}
