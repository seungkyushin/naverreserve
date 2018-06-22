package kr.or.connect.naverreserve.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"kr.or.connect.naverreserve.dao","kr.or.connect.naverreserve.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
