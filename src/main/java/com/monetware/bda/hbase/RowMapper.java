package com.monetware.bda.hbase;

import org.apache.hadoop.hbase.client.Result;

public interface RowMapper<T> {

    T mapRow(Result result, int rowNum) throws Exception;
}
