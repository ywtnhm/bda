package com.monetware.bda.hbase;


import com.monetware.bda.hbase.mapper.RMRBMapper;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;

import java.util.List;

/**
 * @author zengcd 2018/12/15
 * @since 1.0.0
 */
public interface HbaseOperations {

    <T> T execute(String tableName, TableCallback<T> mapper);

    /**
     *
     * @param tableName target table
     * @param family column family
     * @param <T> action type
     * @return a list of objects mapping the scanned rows
     */
    <T> List<T> find(String tableName, String family, final RowMapper<T> mapper);
    /**
     *
     * @param tableName target table
     * @param family column family
     * @param filter filter
     * @param <T> action type
     * @return a list of objects mapping the scanned rows
     */
    <T> List<T> find(String tableName, String family ,final   Filter filter, final RowMapper<T> action);
    /**
     *
     * @param tableName target table
     * @param family column family
     * @param qualifier column qualifier
     * @param <T> action type
     * @return a list of objects mapping the scanned rows
     */
    <T> List<T> find(String tableName, String family, String qualifier, final RowMapper<T> mapper);

    /**
     *
     * @param tableName target table
     * @param family column family
     * @param qualifier column qualifier
     * @param filter filter
     * @param <T> action type
     * @return a list of objects mapping the scanned rows
     */

    <T> List<T> find(String tableName, String family, String qualifier, Filter filter ,final RowMapper<T> mapper);
    /**
     *
     * @param tableName target table
     * @param scan table scanner
     * @param <T> action type
     * @return a list of objects mapping the scanned rows
     */
    <T> List<T> find(String tableName, final Scan scan, final RowMapper<T> mapper);

    /**
     *
     * @param tableName target table
     * @param rowName row name
     * @param mapper row mapper
     * @param <T> mapper type
     * @return object mapping the target row
     */
    <T> T get(String tableName, String rowName, final RowMapper<T> mapper);

    /**
     *
     * @param tableName target table
     * @param rowName row name
     * @param familyName column family
     * @param mapper row mapper
     * @param <T> mapper type
     * @return object mapping the target row
     */
    <T> T get(String tableName, String rowName, String familyName, final RowMapper<T> mapper);

    /**
     *
     * @param tableName target table
     * @param rowName row name
     * @param familyName family
     * @param qualifier column qualifier
     * @param mapper row mapper
     * @param <T> mapper type
     * @return object mapping the target row
     */
    <T> T get(String tableName, final String rowName, final String familyName, final String qualifier, final RowMapper<T> mapper);

    /**
     * 执行put update or delete
     * @param tableName
     * @param action
     */
    void execute(String tableName, MutatorCallback action);

    /**
     *
     * @param tableName
     * @param mutation
     */
    void saveOrUpdate(String tableName, Mutation mutation);

    /**
     *
     * @param tableName
     * @param mutations
     */
    void saveOrUpdates(String tableName, List<Mutation> mutations);

}
