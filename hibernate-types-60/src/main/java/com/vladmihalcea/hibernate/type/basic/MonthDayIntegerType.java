package com.vladmihalcea.hibernate.type.basic;

import com.vladmihalcea.hibernate.type.MutableType;
import com.vladmihalcea.hibernate.type.basic.internal.MonthDayTypeDescriptor;
import com.vladmihalcea.hibernate.type.util.Configuration;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;

import java.time.MonthDay;

/**
 * Maps a Java {@link java.time.MonthDay} object to a {@code INT} column type.
 *
 * @author Mladen Savic (mladensavic94@gmail.com)
 */
public class MonthDayIntegerType extends MutableType<MonthDay, IntegerJdbcType, MonthDayTypeDescriptor> {

    public static final MonthDayIntegerType INSTANCE = new MonthDayIntegerType();

    public MonthDayIntegerType() {
        super(
            MonthDay.class,
            IntegerJdbcType.INSTANCE,
            MonthDayTypeDescriptor.INSTANCE
        );
    }

    public MonthDayIntegerType(Configuration configuration) {
        super(
            MonthDay.class,
            IntegerJdbcType.INSTANCE,
            MonthDayTypeDescriptor.INSTANCE,
            configuration
        );
    }

    public MonthDayIntegerType(org.hibernate.type.spi.TypeBootstrapContext typeBootstrapContext) {
        this(new Configuration(typeBootstrapContext.getConfigurationSettings()));
    }

    public String getName() {
        return "monthday-int";
    }
}
