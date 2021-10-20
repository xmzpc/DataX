### Datax md5transformer
#### 1 快速介绍

md5transformer 插件 实现了对字符串类型的字段进行MD5加密的功能。
#### 2 实现原理

通过继承com.alibaba.datax.transformer.Transformer类并重写evaluate(Record record, Object... paras) 方法实现对指定字段进行加密。

#### 3 功能说明
* 配置一个从Mysql数据库同步抽取数据到本地的作业:

```
{
	"job": {
		"setting": {
			"speed": {
				"channel": 3
			},
			"errorLimit": {
				"record": 0,
				"percentage": 0.02
			}
		},
		"content": [{
			"reader": {
				"name": "mysqlreader",
				"parameter": {
					"username": "root",
					"password": "root",
					"column": [
						"id",
						"name"
					],
					"splitPk": "db_id",
					"connection": [{
						"table": [
							"table"
						],
						"jdbcUrl": [
							"jdbc:mysql://127.0.0.1:3306/database"
						]
					}]
				}
			},
			"writer": {
				"name": "streamwriter",
				"parameter": {
					"print": true
				}
			},
			"transformer": [{
				"name": "md5transformer",
				"parameter": {
					"columnIndex": 1
				}
			}]
		}]
	}
}
```
 
#### 4 参数说明

* name：插件名称【必填】
* parameter：传参【必填】
* columnIndex： 需要加密的字段索引。【必填】

#### 6 性能报告
#### 7 测试报告
