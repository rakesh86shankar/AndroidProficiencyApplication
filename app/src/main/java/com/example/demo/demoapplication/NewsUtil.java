package com.example.demo.demoapplication;

import android.text.TextUtils;

import com.example.demo.demoapplication.modal.NewsList;
import com.example.demo.demoapplication.modal.Row;

import java.util.Iterator;

/**
 * Created by RA283478 on 2/15/2018.
 */

public class NewsUtil {


    /**
     * method to check and remove null data item from list of row data items.
     * @param newsListData
     * @return
     */
    public static NewsList getNonNullData(NewsList newsListData) {

        Iterator<Row> row = newsListData.getRows().iterator();

        while (row.hasNext()) {
            Row rowObject = row.next();
            if ((isNull(rowObject.getDescription())
                    && isNull(rowObject.getTitle())
                    && isNull(rowObject.getImageHref()))) {
                row.remove();
            }
        }
        return newsListData;
    }

    public static boolean isNull(String value) {
        return TextUtils.isEmpty(value);
    }
}
