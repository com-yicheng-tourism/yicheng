package com.yicheng.tourism.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class SubListUtil<T> {

   private static final Logger logger = LoggerFactory.getLogger(SubListUtil.class);

   /**
    * 截取list集合，返回list集合
    *
    * @param tList  (需要截取的集合)
    * @param subNum (每次截取的数量)
    * @return
    */
   public static <T> List<List<T>> subList(List<T> tList, Integer subNum) {
      List<List<T>> tNewList = new ArrayList<List<T>>();
      int priIndex = 0;
      int lastIndex = 0;
      int totalNum = tList.size();
      int insertTimes = totalNum / subNum;
      List<T> subNewList;
      for (int i = 0; i <= insertTimes; i++) {
         priIndex = subNum * i;
         lastIndex = priIndex + subNum;
         if (i == insertTimes) {
            subNewList = tList.subList(priIndex, tList.size());
         } else {
            subNewList = tList.subList(priIndex, lastIndex);
         }
         if (subNewList.size() > 0) {
            tNewList.add(subNewList);
         }
      }
      return tNewList;
   }

   /**
    * 根据List返回平均分布的list(在100个之内)
    * @param list
    * @param <T>
    * @return
    */
   public static <T> List<T> screenOut(List<T> list, Integer averNum) {
      if (list == null || list.isEmpty() || list.size() <= averNum) {
         return list;
      }
      List<T> lastPoint = new ArrayList<>();
      Integer baseNum = 1;
      while (list.size() / baseNum > averNum) {
         baseNum += 1;
      }
      for (int i = 0; i < list.size(); i+= baseNum) {
         lastPoint.add(list.get(i));
      }
      return lastPoint;
   }
}