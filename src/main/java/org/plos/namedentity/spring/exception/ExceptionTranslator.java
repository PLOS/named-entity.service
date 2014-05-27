package org.plos.namedentity.spring.exception;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

/**
 * Transforms a SQLException into a Spring specific DataAccessException. 
 */
public class ExceptionTranslator extends DefaultExecuteListener {

    private static final long serialVersionUID = -2450323227461061152L;

    @Override
    public void exception(ExecuteContext ctx) {
        SQLDialect dialect = ctx.configuration().dialect();
        SQLExceptionTranslator translator = (dialect != null)
                // TODO: fix this (MYSQL -> MySQL)
                //? new SQLErrorCodeSQLExceptionTranslator(dialect.name())
                ? new SQLErrorCodeSQLExceptionTranslator("H2")
                : new SQLStateSQLExceptionTranslator();

        ctx.exception(translator.translate("jOOQ", ctx.sql(), ctx.sqlException()));
    }
}
