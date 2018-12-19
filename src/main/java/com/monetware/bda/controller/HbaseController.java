package com.monetware.bda.controller;


import com.monetware.bda.hbase.HbaseTemplate;
import com.monetware.bda.hbase.mapper.RMRB2Mapper;
import com.monetware.bda.hbase.mapper.RMRBMapper;
import com.monetware.bda.model.WordFrequency;
import com.monetware.bda.util.JsonResult;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hbase")
public class HbaseController {


    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Autowired
    private RMRB2Mapper rmrb2Mapper;

    @Autowired
    private RMRBMapper rmrbMapper;

    @RequestMapping("search")
    @ResponseBody
    public JsonResult searchWord() {

        return new JsonResult("400", "hello search word!");
    }


    @RequestMapping("/")
    public JsonResult index() {
        return new JsonResult("200", "hello hbase");
    }

    @GetMapping("/get")
    @ResponseBody
    public JsonResult get(String rowName) {

        if (null == rowName || "".equals(rowName)) {
            return new JsonResult("400", "please input ?rowName='word' ");
        }
        Map map =new  HashMap();
        map.put("word",rowName);
        Object wordFrequencies = hbaseTemplate.get("resulttest5", rowName, "result", rmrbMapper);

        if(wordFrequencies==null){

            return new JsonResult("400","不存在此词语");
        }else {
            return new JsonResult("200",
                    rowName,
                    wordFrequencies,
                    map);
        }

    }

    @RequestMapping("/findAll")
    public String findAll() {

        return hbaseTemplate.find("resulttest1", "result", rmrbMapper).toString();
    }


    @RequestMapping("/findRowContains")
    public String findRowContains(String subRow) {
        if (null == subRow || "".equals(subRow)) {
            return "please input ?subRow='word' ";
        }
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(subRow));
        return hbaseTemplate.find("resulttest1", "result", filter, rmrbMapper).toString();
    }

    @RequestMapping("/findRowPrefix")
    public String findRowPrefix(String prefix) {
        if (null == prefix || "".equals(prefix)) {
            return "please input ?prefix='word' ";
        }
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes(prefix)));
        return hbaseTemplate.find("resulttest1", "result", filter, rmrbMapper).toString();
    }

}
