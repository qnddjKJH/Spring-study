package com.n2014758030.main;

import com.n2014758030.main.domain.Basic;
import com.n2014758030.main.domain.Profile;
import com.n2014758030.main.repository.BasicRepository;
import com.n2014758030.main.repository.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class MainApplication {

    private BasicRepository basicRepository;
    private ProfileRepository profileRepository;

    public MainApplication(BasicRepository basicRepository, ProfileRepository profileRepository) {
        this.basicRepository = basicRepository;
        this.profileRepository = profileRepository;
    }

    @Bean
    public void init() {
        basicRepository.save(Basic.builder()
                .name("홍길동")
                .label("CEO")
                .email("hong@gmail.com")
                .phone("012-3456-789")
                .build());

        basicRepository.save(Basic.builder()
                .name("장길산")
                .label("CFO")
                .email("jang@gmail.com")
                .phone("123-456-7890")
                .build());

        basicRepository.save(Basic.builder()
                .name("춘향이")
                .label("CTO")
                .email("chun@gmail.com")
                .phone("234-567-8901")
                .build());

        profileRepository.save(Profile.builder()
                .network("트위터")
                .userName("@home")
                .url("https://twitter/@home")
                .createDate(LocalDateTime.now())
                .build());

        profileRepository.save(Profile.builder()
                .network("페이스북")
                .userName("@home")
                .url("https://facebook/@home")
                .createDate(LocalDateTime.now())
                .build());

        profileRepository.save(Profile.builder()
                .network("인스타그램")
                .userName("@home")
                .url("https://instagram/@home")
                .createDate(LocalDateTime.now())
                .build());
    }

    @Bean
    public CommandLineRunner runner(BasicRepository basicRepository, ProfileRepository profileRepository) {
        return (args) -> IntStream.rangeClosed(1, 3).forEach(index -> {
            basicRepository.save(Basic.builder()
                    .name("dummy" + index)
                    .label("dummy" + index)
                    .email("dummy" + index + "@dummy.com")
                    .phone("dummy-" + index)
                    .build());

            profileRepository.save(Profile.builder()
                    .network("dummy" + index)
                    .userName("@dummy" + index)
                    .url("https://dummy/@dummy" + index)
                    .createDate(LocalDateTime.now())
                    .build());
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
