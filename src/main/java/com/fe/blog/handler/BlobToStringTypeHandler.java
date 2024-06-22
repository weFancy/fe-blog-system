package com.fe.blog.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * @author Fancy.we
 */
@MappedJdbcTypes(JdbcType.BLOB)
public class BlobToStringTypeHandler extends BaseTypeHandler<String> {
    private static final String DEFAULT_CHARSET = "utf-8";
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ByteArrayInputStream inputStream;
        byte[] bytes;
        try {
            bytes = parameter.getBytes(DEFAULT_CHARSET);
            // 把String转化成byte流
            inputStream = new ByteArrayInputStream(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
        preparedStatement.setBinaryStream(i,inputStream,bytes.length);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Blob blob = resultSet.getBlob(columnName);
        return getBlob2String(blob);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Blob blob =resultSet.getBlob(columnIndex);
        return getBlob2String(blob);
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Blob blob = callableStatement.getBlob(columnIndex);
        return getBlob2String(blob);
    }

    private String getBlob2String(Blob blob) throws SQLException{
        byte[] returnValue = null;
        String result = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            if (null != returnValue) {
                // 把byte转化成string
                result = new String(returnValue, DEFAULT_CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
        return result;
    }

}
