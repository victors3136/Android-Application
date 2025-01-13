package ubb.server.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ubb.server.data.Album;

@Configuration
public class InjectionService {

    @Bean
    public Map<Integer, Album> storage() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public PrintStream out() {
        return System.out;
    }

    @Bean
    public LoggingService logger() {
        return new LoggingService(out());
    }
}
