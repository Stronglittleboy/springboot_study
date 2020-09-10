package com.hlw.springboot_study;

import com.hlw.springboot_study.redis_message.Receiver;
import com.hlw.springboot_study.service.bo.Quote;
import com.hlw.springboot_study.uploadfile.StorageProperties;
import com.hlw.springboot_study.uploadfile.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
@Slf4j
@EnableConfigurationProperties(StorageProperties.class)
public class SpringbootStudyApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(SpringbootStudyApplication.class, args);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);
        for (int i = 0; i < 100; i++) {
             new Thread(() -> {
                log.info("获取的token：{}",getName(template, "54545"));
            }).start();
            new Thread(() -> {
                log.info("获取的token：{}",getName(template, "26598"));
//                System.out.println(getName(template, "26598"));
            }).start();
        }
//        while (receiver.getCount() == 0) {
//            log.info("Sending message...");
//            template.convertAndSend("chat", "Hello from Redis!");
//            Thread.sleep(500L);
//        }
//        System.exit(0);
    }

    private static String getName(StringRedisTemplate template,String value) {
        log.info("插入的值:{}",value);
        String lou = template.opsForValue().get("lou");
        log.info("获取的值{}",lou);
        if (null == lou || StringUtils.isEmpty(lou)){
            try {
                System.out.println("开始休眠");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            template.opsForValue().set("lou",value,1, TimeUnit.SECONDS);
            log.info("真实插入的值,:{}",value);
            return value;
        }
        return lou;
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Quote quote = restTemplate.getForObject(
                    "https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            assert quote != null;
            log.info(quote.toString());
        };
    }
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return factory;
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
