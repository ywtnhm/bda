package com.monetware.bda.hbase;


import org.springframework.dao.UncategorizedDataAccessException;

/**
 * @author zengcd 2018/12/15
 * @since 1.0.0
 */
public class HbaseSystemException extends UncategorizedDataAccessException {

    public HbaseSystemException(Exception cause) {
        super(cause.getMessage(), cause);
    }

    public HbaseSystemException(Throwable throwable) {

        super(throwable.getMessage(), throwable);

    }
}