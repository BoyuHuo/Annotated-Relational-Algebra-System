package com.comp6591.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    private Map<String, List<Map<String, String>>> data;
    private static DataManager instance;

    private DataManager() {
        data = new HashMap<String, List<Map<String, String>>>();
    }


    public Map<String, List<Map<String, String>>> getData() {
        return data;
    }

    public void setData(Map<String, List<Map<String, String>>> data) {
        this.data = data;
    }

    public DataManager getInstance() {
        return instance;
    }

    public void setInstance(DataManager instance) {
        this.instance = instance;
    }
}
