package com.monetware.bda.controller;


import com.monetware.bda.hbase.HbaseTemplate;
import com.monetware.bda.hbase.mapper.RMRBMapper;
import com.monetware.bda.util.JsonResult;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hbase")
public class HbaseController {


    @Autowired(required = true)
    private HbaseTemplate hbaseTemplate;

    @Autowired
    private RMRBMapper rmrbMapper;

    @RequestMapping("search")
    @ResponseBody
    public JsonResult searchWord() {

        return new JsonResult(false, "hello search word!");
    }


    @RequestMapping("/")
    public JsonResult index() {
        return new JsonResult(true, "hello hbase");
    }

    @RequestMapping("/get")
    public String get(String rowName) {

        if (null == rowName || "".equals(rowName)) {
            return "please input ?rowName='word' ";
        }
        return hbaseTemplate.get("resulttest1", rowName, "result", rmrbMapper).toString();
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


}
