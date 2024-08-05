package com.dvp.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class ScalarConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.GraphQLLong);
    }

    @Bean
    public GraphQLScalarType date(){
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQLScalarType dateTime(){
        return ExtendedScalars.DateTime;
    }
}
