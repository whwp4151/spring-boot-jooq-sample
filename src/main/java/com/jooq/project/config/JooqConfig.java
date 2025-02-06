package com.jooq.project.config;

import org.jooq.conf.ExecuteWithoutWhere;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.RenderTable;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {
    @Bean
    public DefaultConfigurationCustomizer jooqDefaultConfigurationCustomizer() {
        return configuration -> configuration.settings()
                .withExecuteDeleteWithoutWhere(ExecuteWithoutWhere.THROW)  // 조건절 없이 delete 발생시 예외 발생
                .withExecuteUpdateWithoutWhere(ExecuteWithoutWhere.THROW)  // 조건절 없이 update 발생시 예외 발생
                .withRenderQuotedNames(RenderQuotedNames.NEVER) // 쿼리 로그에서 칼럼을 ""으로 감싸서 표시 여부
                .withRenderTable(RenderTable.WHEN_MULTIPLE_TABLES) // 쿼리 로그에서 칼럼 앞에 테이블명 포함해서 표시 여부
                .withRenderFormatted(true)  // SQL 로깅 포맷팅
                .withBatchSize(100) // 배치 크기 설정
                .withQueryTimeout(60); // SQL 실행 타임아웃;
    }
}
