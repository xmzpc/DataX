package com.alibaba.datax.plugin.transformer.md5transformer;

import com.alibaba.datax.common.element.Column;
import com.alibaba.datax.common.element.Record;
import com.alibaba.datax.common.element.StringColumn;
import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.core.transport.transformer.TransformerErrorCode;
import com.alibaba.datax.transformer.Transformer;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * 以插件形式注册Transformer时，名称不能为dx_开头
 *
 * @Title: MD5Transformer
 * @Description: MD5Transformer.java 提供对指定字段进行MD5加密的功能
 * @Author xmz
 * @Date: 2021/10/19 14:17
 * @see com.alibaba.datax.core.transport.transformer.TransformerRegistry#loadTransformerFromLocalStorage(List)
 */
public class MD5Transformer extends Transformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Transformer.class);

    public MD5Transformer() {
        setTransformerName("md5transformer");
        LOGGER.info("using md5transformer transformer");
    }

    @Override
    public Record evaluate(Record record, Object... paras) {
        int columnIndex;
        try {
            if (paras.length < 1) {
                throw new RuntimeException("md5transformer 缺少参数");
            }
            columnIndex = (Integer) paras[0];
        } catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_ILLEGAL_PARAMETER, "paras:" + Arrays.asList(paras).toString() + " => " + e.getMessage());
        }
        Column column = record.getColumn(columnIndex);
        try {
            String originalValue = column.asString();
            if (originalValue == null) {
                return record;
            }
            if (Objects.equals(column.getType(), Column.Type.STRING)) {
                //执行MD5加密
                String newValue = DigestUtils.md5Hex(originalValue);
                StringColumn stringColumn = new StringColumn(newValue);
                record.setColumn(columnIndex, stringColumn);
            }
        } catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_RUN_EXCEPTION, e.getMessage(), e);
        }
        return record;
    }
}
