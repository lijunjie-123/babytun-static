package com.itlaoqi.babytun.service;

import com.itlaoqi.babytun.dao.*;
import com.itlaoqi.babytun.entity.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    private GoodsDAO goodsDAO;
    @Resource
    private GoodsCoverDAO goodsCoverDAO;
    @Resource
    private GoodsDetailDAO goodsDetailDAO;
    @Resource
    private GoodsParamDAO goodsParamDAO;
    @Resource
    private EvaluateDAO evaluateDAO;

    //@Cacheable是声明式缓存的核心注解
    //第一次访问的时候将方法的返回结果放入缓存
    //第二次访问时不再执行方法内部的代码，而是从缓存中直接提取数据。
    @Cacheable(value="goods" , key = "#goodsId") //key:  goods::1  goods::2
    public Goods getGoods(Long goodsId) {
        return goodsDAO.findById(goodsId);
    }

    @Cacheable(value="covers" , key = "#goodsId")
    public List<GoodsCover> findCovers(Long goodsId){
        return goodsCoverDAO.findByGoodsId(goodsId);
    }

    @Cacheable(value="details" , key = "#goodsId")
    public List<GoodsDetail> findDetails(Long goodsId){
        return  goodsDetailDAO.findByGoodsId(goodsId);
    }

    @Cacheable(value="params" , key = "#goodsId")
    public List<GoodsParam> findParams(Long goodsId){
        List list =  goodsParamDAO.findByGoodsId(goodsId);
        return list;
    }
    public List<Goods> findAllGoods(){
        return goodsDAO.findAll();
    }

    public List<Goods> findLast5M(){
        return goodsDAO.findLast5M();
    }

    public List<Evaluate> findEvaluates(Long goodsId){
        return evaluateDAO.findByGoodsId(goodsId);
    }

}
