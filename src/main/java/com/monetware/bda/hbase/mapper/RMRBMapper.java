package com.monetware.bda.hbase.mapper;

import com.monetware.bda.hbase.RowMapper;
import com.monetware.bda.model.RMRB;
import com.monetware.bda.model.WordFrequency;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RMRBMapper implements RowMapper {

    private final String s1 = ",";
    private final String s2 = "-";
    private final String s3 = "0";
    private final String s4 = "";

    @Override
    public List<WordFrequency> mapRow(Result result, int rowNum) throws Exception {
        if (result == null) {
            return null;
        }
        List<WordFrequency> wordFrequencies = new ArrayList<>();

        String allData = Bytes.toString(result.getValue(Bytes.toBytes("result"), Bytes.toBytes("date")));
        if (allData == null || "".equals(allData)) {
            return null;
        }

        for (String data : allData.split("/")) {
            boolean flag = true;

            String year = data.split(s1)[0].split(s2)[0];
            String month="";
            if(data.split(s1)[0].split(s2)[1].startsWith("0")){
                month= data.split(s1)[0].split(s2)[1].replace(s3, s4);
            }else {
                month= data.split(s1)[0].split(s2)[1];
            }

            String dateStr = year + s2 + month;
            for (int i = 0; i < wordFrequencies.size(); i++) {

                if (wordFrequencies.get(i).getDateStr().equals(dateStr)) {
                    wordFrequencies.get(i).setCount(wordFrequencies.get(i).getCount() +
                            Integer.parseInt(data.split(",")[1]));
                    flag = false;
                    break;
                }

            }
            if (flag) {
                WordFrequency wordFrequency = new WordFrequency();
                wordFrequency.setDateStr(dateStr);
                wordFrequency.setCount(Integer.parseInt(data.split(",")[1]));
//            int year=Integer.parseInt(wordFrequency.getDateStr().split("-")[0]);
//            if(year<2004){
                wordFrequencies.add(wordFrequency);
                // }
            }
        }
        //1945-2018
        for (int i = 1946; i < 2004; i++) {
            for (int j = 1; j < 13; j++) {
                boolean flag=true;
                String key = i + s2 + j;
                for (int k = 0; k < wordFrequencies.size(); k++) {
                    if (wordFrequencies.get(k).getDateStr().equals(key)) {
                        flag=false;
                        break;
                    }

                }
                if(flag){
                    WordFrequency wordFrequency = new WordFrequency();
                    wordFrequency.setCount(0);
                    wordFrequency.setDateStr(key);
                    wordFrequencies.add(wordFrequency);
                }

            }
        }
        Collections.sort(wordFrequencies, (obj1, obj2) -> {
            int year = Integer.parseInt(obj1.getDateStr().split("-")[0]) - Integer.parseInt((obj2).getDateStr().split("-")[0]);
            int month = Integer.parseInt(obj1.getDateStr().split("-")[1]) - Integer.parseInt((obj2).getDateStr().split("-")[1]);
            if (year > 0 || year == 0 && month > 0) {
                return 1;
            } else if (year == 0 && month == 0) {
                return 0;
            } else {
                return -1;
            }
        });
//
//        List<WordFrequency> newwordFrequencies = new ArrayList<>();
//        for (int i = 0; i < wordFrequencies.size(); i++) {
//            WordFrequency wordFrequency = new WordFrequency();
//            wordFrequency.setCount(wordFrequencies.get(i).getCount());
//            wordFrequency.setDateStr(wordFrequencies.get(i).getDateStr());
//            newwordFrequencies.add(wordFrequency);
//            for (int j = 0; j < wordFrequencies.size(); j++) {
//            if (wordFrequencies.get(i).getDateStr().equals(wordFrequencies.get(i).getDateStr())) {
//
//            }
//            }
//
//
//        }
        return wordFrequencies;
    }
}
