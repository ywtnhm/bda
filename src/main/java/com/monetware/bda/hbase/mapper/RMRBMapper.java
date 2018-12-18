package com.monetware.bda.hbase.mapper;

import com.monetware.bda.hbase.RowMapper;
import com.monetware.bda.model.RMRB;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

@Component
public class RMRBMapper implements RowMapper<RMRB> {
    @Override
    public RMRB mapRow(Result result, int rowNum) throws Exception {

        RMRB rmrb = new RMRB();
        System.out.println(Bytes.toString(result.getRow()));
        System.out.println(Bytes.toString(result.getValue(Bytes.toBytes("result"), Bytes.toBytes("date"))));
        rmrb.setDateStr(Bytes.toString(result.getValue(Bytes.toBytes("result"), Bytes.toBytes("date"))));
        rmrb.setWord(Bytes.toString(result.getRow()));
        return rmrb;
    }
}
