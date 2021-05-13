package com.tracelink.prodsec.synapse.test;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.read.CyclicBufferAppender;
import java.util.concurrent.Executors;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class to initialize Spring Boot app during controller tests.
 */
@SpringBootApplication(scanBasePackages = "com.tracelink.prodsec.synapse")
@EntityScan(basePackages = "com.tracelink.prodsec.synapse")
@EnableJpaRepositories(basePackages = "com.tracelink.prodsec.synapse")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TestSynapseBootApplication {

	private static final String APPENDER_NAME = "ROLLING-QUEUE";
	private static final String PATTERN_LAYOUT = "%msg%n";

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public Logger appLogger() {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		return context.getLogger("com.tracelink.prodsec");
	}

	@Bean
	public CyclicBufferAppender<ILoggingEvent> appender() {
		return (CyclicBufferAppender<ILoggingEvent>) appLogger().getAppender(APPENDER_NAME);
	}

	@Bean
	public LayoutWrappingEncoder<ILoggingEvent> encoder() {
		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(appLogger().getLoggerContext());
		encoder.setPattern(PATTERN_LAYOUT);
		encoder.start();
		return encoder;
	}

	@Bean
	public ConcurrentTaskScheduler scheduler() {
		return new ConcurrentTaskScheduler(Executors.newScheduledThreadPool(2));
	}
}
