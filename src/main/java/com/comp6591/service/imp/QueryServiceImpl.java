package com.comp6591.service.imp;

import com.comp6591.entity.DataManager;
import com.comp6591.entity.Record;
import com.comp6591.entity.Table;
import com.comp6591.service.QueryService;
import com.comp6591.utils.Util;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class QueryServiceImpl implements QueryService {


    public Stack<Table> doQuery(String input) {

        Stack<String> inputStack = new Stack<>();
        Stack<Table> tableStack = new Stack<>();

        buildInputStack(input, inputStack);

        //project <A,C> ( q join r )
        List<String> keys = new ArrayList<>();

        while (!inputStack.empty()) {

            String token = inputStack.pop();
            if (token.equals("project")) {
                Table table = project(keys, tableStack.pop());
                tableStack.push(table);
            } else if (token.startsWith("<") && token.endsWith(">")) {
                String[] newKeys = token.substring(1, token.length() - 1).split(",");
                Arrays.stream(newKeys).forEach((str) -> {
                    str.replace(" ", "");
                });
                keys = new ArrayList<>(Arrays.asList(newKeys));

            } else if (token.endsWith("join")) {
                Table rTable = tableStack.pop();
                String lTableName = inputStack.pop();
                Table lTable = DataManager.getInstance().getData().get(lTableName);
                tableStack.push(naturalJoin(lTable, rTable));
            } else if (token.equals(")")) {

            } else if (token.equals("(")) {

            } else {
                tableStack.push(DataManager.getInstance().getData().get(token));
            }
        }
        return tableStack;
    }

    private void buildInputStack(String input, Stack<String> inputStack) {
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            inputStack.push(token);
        }
    }

    public Table naturalJoin(Table lTable, Table rTable) {

        List<String> keys = new ArrayList<>();
        if (lTable.getRecords().size() == 0 || rTable.getRecords().size() == 0) {
            return null;
        }

        lTable.getRecords().get(0).getFields().forEach((lKey, lValue) -> {
            rTable.getRecords().get(0).getFields().forEach((rKey, rValue) -> {
                if (lKey.equals(rKey)) {
                    keys.add(lKey);
                }
            });
        });

        Table result = new Table();
        lTable.getRecords().forEach(lRecord -> {
            rTable.getRecords().forEach(rRecord -> {

                if (isEqual(keys, lRecord, rRecord)) {
                    result.getRecords().add(joinRecord(keys, lRecord, rRecord));
                }

            });
        });

        return result;
    }

    public Table project(List<String> keys, Table table) {
        Table result = new Table();

        table.getRecords().forEach(record -> {
            Record newRecord = new Record();
            record.getFields().forEach((key, value) -> {
                if (keys.contains(key.trim())) {
                    newRecord.getFields().put(key, value);
                }
            });
            if (newRecord.getFields().size() > 0) {
                result.getRecords().add(newRecord);
            }
        });
        return result;
    }

    private boolean isEqual(List<String> keys, Record lRecord, Record rRecord) {

        for (int i = 0; i < keys.size(); i++) {
            if (!lRecord.getFields().get(keys.get(i)).equals(rRecord.getFields().get(keys.get(i)))) {
                return false;
            }
        }

        return true;
    }

    private Record joinRecord(List<String> keys, Record lRecord, Record rRecord) {

        Record result = Util.deepCopyRecord(lRecord);
        rRecord.getFields().forEach((key, value) -> {
            if (!keys.contains(key)) {
                result.getFields().put(key, rRecord.getFields().get(key));
            }
        });
        return result;
    }

}
