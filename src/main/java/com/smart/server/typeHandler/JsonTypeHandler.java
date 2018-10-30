package com.smart.server.typeHandler;

import com.smart.server.utils.JsonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return (T) JsonUtils.parseObject(rs.getString(columnName), getGenericClass());
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return (T) JsonUtils.parseObject(rs.getString(columnIndex), getGenericClass());
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return (T) JsonUtils.parseObject(cs.getString(columnIndex), getGenericClass());
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, JsonUtils.toJSONString(parameter));
    }

    public Class getGenericClass() {
        Class clazz = getClass();
        while (clazz != Object.class) {
            Type t = clazz.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] args = ((ParameterizedType) t).getActualTypeArguments();
                if (args[0] instanceof Class) {
                    return (Class<T>) args[0];
                }
            }
        }
        return null;
    }
}
