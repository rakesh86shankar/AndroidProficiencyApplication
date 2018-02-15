package com.example.demo.demoapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.demo.demoapplication.modal.NewsList;
import com.example.demo.demoapplication.modal.Row;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.demo.demoapplication", appContext.getPackageName());
    }

    @Test
    public void newsData_1() throws Exception{
        NewsList newsListData = new NewsList();
        Row row1 = new Row();
        row1.setTitle("TestTitle");
        row1.setDescription("TestTitleDescription");
        row1.setImageHref("http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg");
        List<Row> rows = new ArrayList<>();
        rows.add(row1);
        Row row2 = new Row();
        row2.setTitle("");
        row2.setDescription(null);
        rows.add(row2);
        newsListData.setRows(rows);
        NewsUtil.getNonNullData(newsListData);
        assertEquals(1,newsListData.getRows().size());
    }

    @Test
    public void newsData_Success() throws Exception{
        NewsList newsListData = new NewsList();
        Row row1 = new Row();
        row1.setTitle("TestTitle1");
        row1.setDescription("TestTitleDescription1");
        row1.setImageHref("http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg");
        List<Row> rows = new ArrayList<>();
        rows.add(row1);
        Row row2 = new Row();
        row2.setTitle("TestTitle1");
        row2.setDescription("TestTitleDescription1");
        row2.setImageHref("http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg");
        newsListData.setRows(rows);
        NewsUtil.getNonNullData(newsListData);
        assertEquals(2,newsListData.getRows().size());
    }


}
