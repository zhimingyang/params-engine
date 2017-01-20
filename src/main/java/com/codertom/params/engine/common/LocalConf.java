package com.codertom.params.engine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地资源抽象集合
 */
public class LocalConf {


    private List<Path> paths;

    private String localPath;

    private static Logger logger = LoggerFactory.getLogger(LocalConf.class);

    public LocalConf(String confDirPath){
        this(confDirPath,false);
    }
    public LocalConf(String confDirPath,boolean lazy){
        paths = new ArrayList<Path>();
        File confDirFile = new File(confDirPath);
        if(!confDirFile.exists()){
            if (logger.isWarnEnabled())
                logger.warn("local params conf dir is not exist input path is {}",new Object[]{confDirPath});
            return;
        }
        if (!confDirFile.isDirectory()){
            if (logger.isWarnEnabled())
                logger.warn("local params conf dir is not a directory path is {}",new Object[]{confDirPath});
            return;
        }
        //过滤可用的File,并进行加载
        loadAllParamConf(filterParamsConf(confDirFile));

    }

    /**
     * 过滤参数配置文件中的文件,暂时只支持单层的文件，不支持递归嵌套
     * @param confParamDirFile
     * @return
     */
    private List<File> filterParamsConf(File confParamDirFile){
        List<File> effictivefiles = new ArrayList<File>(40);
        File[] subConfFiles = confParamDirFile.listFiles();
            for (File f:subConfFiles){
                if (f.exists()&&f.isFile()&&f.getName().endsWith(".xml")){
                    if (logger.isInfoEnabled())
                        logger.info("load param file {} from {}",new Object[]{f.getName(),confParamDirFile.getName()});
                    effictivefiles.add(f);
                }

            }
            return effictivefiles;
    }

    /**
     * 将Params 配置文件进行解析加载
     * @param effictiveFiles
     */
    private void loadAllParamConf(List<File> effictiveFiles){
        for(File confFile:effictiveFiles){
            StringBuffer buffer = new StringBuffer();
            try {
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(confFile)));
                String content = "";
                while ((content=fileReader.readLine())!=null){
                    buffer.append(content);
                }
            } catch (FileNotFoundException e) {
                logger.error("file not found exception ",e);
            } catch (IOException e) {
                logger.error("read param conf File error ",e);
            }

            URL url = new URL();
            url.setPath(confFile.getPath());
            String fileAllContant = buffer.toString();
            Path path = new Path(fileAllContant,url);
            paths.add(path);
        }
    }

    public List<Path> getPaths() {
        return paths;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
