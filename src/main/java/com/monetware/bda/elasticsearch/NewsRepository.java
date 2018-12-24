package com.monetware.bda.elasticsearch;

import com.monetware.bda.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface NewsRepository extends Repository<News, String> {

    List<News> findByNameAndPrice(String name, Integer price);

    List<News> findByNameOrPrice(String name, Integer price);

    Page<News> findByName(String name, Pageable page);

    Page<News> findByNameNot(String name, Pageable page);

    Page<News> findByPriceBetween(int price, Pageable page);

    Page<News> findByNameLike(String name, Pageable page);

    @Query("{\"bool\" : {\"must\" : {\"term\" : {\"message\" : \"?0\"}}}}")
    Page<News> findByMessage(String message, Pageable pageable);
}