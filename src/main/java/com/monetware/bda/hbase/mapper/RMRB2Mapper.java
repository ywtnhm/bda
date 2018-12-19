package com.monetware.bda.hbase.mapper;

import com.monetware.bda.hbase.RowMapper;
import com.monetware.bda.model.WordFrequency;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RMRB2Mapper implements RowMapper {
    @Override
    public Map mapRow(Result result, int rowNum) throws Exception {

        if (result == null) {
            return null;
        }
        Map map = new TreeMap();
        String allData = Bytes.toString(result.getValue(Bytes.toBytes("result"), Bytes.toBytes("date")));
        if (allData == null || "".equals(allData)) {
            return null;
        }
        for (String data : allData.split("/")) {
            map.put(data.split(",")[0], data.split(",")[1]);
        }
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());

        Collections.sort(list, (Map.Entry<String, String> obj1, Map.Entry<String, String> obj2) -> {
            int year = Integer.parseInt(obj1.getKey().split("-")[0]) - Integer.parseInt(obj2.getKey().split("-")[0]);
            int month = Integer.parseInt(obj1.getKey().split("-")[1]) - Integer.parseInt(obj2.getKey().split("-")[1]);
            if (year > 0 || year == 0 && month > 0) {
                return 1;
            } else if (year == 0 && month == 0) {
                return 0;
            } else {
                return -1;
            }
        });
        return map;
    }
}
