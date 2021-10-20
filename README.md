![Datax-logo](https://github.com/alibaba/DataX/blob/master/images/DataX-logo.jpg)


# DataX

DataX 是阿里云 [DataWorks数据集成](https://www.aliyun.com/product/bigdata/ide) 的开源版本，在阿里巴巴集团内被广泛使用的离线数据同步工具/平台。DataX 实现了包括 MySQL、Oracle、OceanBase、SqlServer、Postgre、HDFS、Hive、ADS、HBase、TableStore(OTS)、MaxCompute(ODPS)、Hologres、DRDS 等各种异构数据源之间高效的数据同步功能。


# Features

DataX本身作为数据同步框架，将不同数据源的同步抽象为从源头数据源读取数据的Reader插件，以及向目标端写入数据的Writer插件，理论上DataX框架可以支持任意数据源类型的数据同步工作。同时DataX插件体系作为一套生态系统, 每接入一套新数据源该新加入的数据源即可实现和现有的数据源互通。

# What's New

- 适配SAP HANA读写插件，详见hanareader、hanawriter
- Postgresql、Oracle支持on duplicate key update（当主键冲突update数据）模式，在job.json配置writeMode为update(key1,key2,key3)
- 新增md5md5transformer插件，支持对字符串格式的字段进行md5加密，以插件的形式在运行时加载进DataX框架。

# Blog

[DataX二次开发-适配自定义数据源](https://www.cnblogs.com/xmzpc/p/15428692.html)

[DataX二次开发-支持writeMode配置update](https://www.cnblogs.com/xmzpc/p/15429291.html)

[DataX二次开发-自定义Transformer并运行时加载](https://www.cnblogs.com/xmzpc/p/15429546.html)


# DataX详细介绍

##### 请参考：[DataX-Introduction](https://github.com/alibaba/DataX/blob/master/introduction.md)


# Quick Start

##### 请点击：[Quick Start](https://github.com/alibaba/DataX/blob/master/userGuid.md)

## Java本地启动
使用打包命令
> mvn -U clean package assembly:assembly -Dmaven.test.skip=true

打好包后填写datax路径dataxHome和job.json路径jobJsonPath即可。
``` java
    public static void main(String[] args) {
        String dataxHome="";
        String jobJsonPath="";
        System.setProperty("datax.home", dataxHome);
        String[] datxArgs = {"-job", jobJsonPath, "-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
```

# Support Data Channels 

DataX目前已经有了比较全面的插件体系，主流的RDBMS数据库、NOSQL、大数据计算系统都已经接入，目前支持数据如下图，详情请点击：[DataX数据源参考指南](https://github.com/alibaba/DataX/wiki/DataX-all-data-channels)




# 我要开发新的插件

请点击：[DataX插件开发宝典](https://github.com/alibaba/DataX/blob/master/dataxPluginDev.md)


# License

This software is free to use under the Apache License [Apache license](https://github.com/alibaba/DataX/blob/master/license.txt).

