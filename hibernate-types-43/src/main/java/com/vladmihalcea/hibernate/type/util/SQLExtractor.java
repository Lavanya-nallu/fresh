package com.vladmihalcea.hibernate.type.util;

/**
 * The {@link SQLExtractor} allows you to extract the
 * underlying SQL query generated by a JPQL or JPA Criteria API query.
 * <p>
 * For more details about how to use it, check out <a href="https://vladmihalcea.com/get-sql-from-jpql-or-criteria/">this article</a> on <a href="https://vladmihalcea.com/">vladmihalcea.com</a>.
 *
 * @deprecated use {@link com.vladmihalcea.hibernate.query.SQLExtractor} instead
 *
 * @author Vlad Mihalcea
 * @since 2.9.11
 */
@Deprecated
public class SQLExtractor extends com.vladmihalcea.hibernate.query.SQLExtractor {

    private SQLExtractor() {
        throw new UnsupportedOperationException("SQLExtractor is not instantiable!");
    }
}
