package com.comp6591.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataManager {
    private Map<String,Table> data;
    private static DataManager instance;

    private DataManager() {
        data = new HashMap<String, Table>();
    }


    public Map<String, Table> getData() {
        return data;
    }

    public void addDataTable(String tableName,Table data) {
        this.data.put(tableName,data);
    }

    synchronized static public DataManager getInstance() {
        if(instance == null){
            instance = new DataManager();
        }
        return instance;
    }

    public Set<String> getTableList(){
        return instance.getData().keySet();
    }

}
