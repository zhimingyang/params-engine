package com.codertom.params.engine.rulemanager;

import com.codertom.params.engine.type.BasicType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规则解析器
 */
public class RuleParser {

    private static Logger logger = LoggerFactory.getLogger(RuleParser.class);

    private Map<String,List<BasicType>> typeMap;

    public RuleParser(){
        typeMap = new HashMap<String,List<BasicType>>();
    }
    /**
     * 对配置中的所有文件进行解析
     * @param filePath
     * @return
     */
    public Map<String,List<BasicType>> parseRules(String filePath){
        File xmlFile = new File(filePath);
        if(!xmlFile.exists())
            return null;
        if (xmlFile.isDirectory()){
            File[] files = xmlFile.listFiles();
            for (File f:files){
                if (f.exists()&&f.getName().endsWith(".xml")){

                }
            }
        }
        return null;
    }

    /**
     * 解析单个文件
     * @param f
     * @param m
     */
    public void doParseFile(File f,Map m) throws FileNotFoundException {

        if (f==null||!f.exists()||f.isDirectory())
            return;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            logger.warn("target file not exits file name is {}",new Object[]{f.getName()});
            throw new FileNotFoundException("目标文件不存在");
        }
        SAXReader saxReader = new SAXReader();
        Document document =null;
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            logger.error("build document error",e);
            return;
        }
        Element rootElement = document.getRootElement();



    }

}
