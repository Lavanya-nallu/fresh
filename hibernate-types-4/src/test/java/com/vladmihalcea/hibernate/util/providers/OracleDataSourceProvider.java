package com.vladmihalcea.hibernate.util.providers;

import com.vladmihalcea.hibernate.util.ReflectionUtils;
import org.hibernate.dialect.Oracle10gDialect;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Vlad Mihalcea
 */
public class OracleDataSourceProvider implements DataSourceProvider {
    @Override
    public String hibernateDialect() {
        return Oracle10gDialect.class.getName();
    }

    @Override
    public DataSource dataSource() {
        try {
            DataSource dataSource = ReflectionUtils.newInstance("oracle.jdbc.pool.OracleDataSource");
            ReflectionUtils.invokeSetter(dataSource, "databaseName", "high_performance_java_persistence");
            ReflectionUtils.invokeSetter(dataSource, "URL", url());
            ReflectionUtils.invokeSetter(dataSource, "user", "oracle");
            ReflectionUtils.invokeSetter(dataSource, "password", "admin");
            return dataSource;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Class<? extends DataSource> dataSourceClassName() {
        return ReflectionUtils.getClass("oracle.jdbc.pool.OracleDataSource");
    }

    @Override
    public Properties dataSourceProperties() {
        Properties properties = new Properties();
        properties.setProperty("databaseName", "high_performance_java_persistence");
        properties.setProperty("URL", url());
        properties.setProperty("user", username());
        properties.setProperty("password", password());
        return properties;
    }

    @Override
    public String url() {
        return "jdbc:oracle:thin:@localhost:1521/xe";
    }

    @Override
    public String username() {
        return "oracle";
    }

    @Override
    public String password() {
        return "admin";
    }

    @Override
    public Database database() {
        return Database.ORACLE;
    }
}
