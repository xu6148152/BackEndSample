package com.binea.interceptor;

import com.binea.utils.Paginator;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * Paging interceptor
 * Created by binea
 * Date: 28/11/2017
 * TIME: 10:16 PM
 */
@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class})
})
public class PageInterceptor implements Interceptor {

    private String pageSqlId;

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                                                     SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                                                     new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();
        if (id.matches(pageSqlId)) {
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            Map<?, ?> parameter = (Map<?, ?>) boundSql.getParameterObject();
            if (parameter != null) {
                Paginator paginator = (Paginator) parameter.get("paginator");
                if (paginator != null) {
                    String pageSql = sql + " limit " + (paginator.getPage() - 1) * paginator.getRows() + "," + paginator.getRows();
                    metaObject.setValue("delegate.boundSql", pageSql);
                }
            }
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.pageSqlId = properties.getProperty("pageSqlId");
    }
}
