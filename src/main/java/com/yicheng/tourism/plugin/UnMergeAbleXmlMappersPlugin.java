package com.yicheng.tourism.plugin;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * @Author
 * @Date
 * @Desc:  重写mybatis-generator插件 解决生成的 *.xml重复追加的问题
 * @Version v1.0.0
 */
public class UnMergeAbleXmlMappersPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        sqlMap.setMergeable(false);
        return true;
    }
}
