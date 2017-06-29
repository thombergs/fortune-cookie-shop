package de.adesso.cookies.quotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableHystrix
@EnableCaching
public class ProductServiceApplication {

    public static final String COOKIES = "COOKIES_";

    private Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(COOKIES);
    }

    @CacheEvict(allEntries = true, value = {COOKIES})
    @Scheduled(fixedDelay=5000)
    public void reportCacheEvict() {
        logger.info("Flush '{}' Cache!", COOKIES);
    }

}
