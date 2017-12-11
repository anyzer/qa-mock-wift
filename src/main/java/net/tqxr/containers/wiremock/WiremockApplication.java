package net.tqxr.containers.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.common.Notifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootApplication
@ComponentScan
public class WiremockApplication {


    private WireMockServer wireMockServer;

    @PostConstruct
    void afterConstruct() {
        wireMockServer.start();

    }

    WiremockApplication(WiremockSettings settings) {
        System.out.println(String.format("ROOTDIRECTORYR: %s", settings.rootDirectory));

        Notifier n = new Notifier() {
            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void error(String message) {
                System.out.println(message);
            }

            @Override
            public void error(String message, Throwable t) {
                System.out.println(message);
            }
        };

        wireMockServer = new WireMockServer(
                wireMockConfig()
                        .port(8251)
//                        .extensions(new FtlTransformer())
                        .withRootDirectory(settings.rootDirectory)
                        .notifier(n)
                        .notifier(new ConsoleNotifier(true))
        );
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
//            configureFor(8089);
//            givenThat(
//                    get(urlEqualTo("/this/now/rocks"))
//                            .willReturn(
//                                    aResponse()
//                                            .withHeader("Content-type", "application/json")
//                                            .withTransformerParameter("newValue", 66)
//                                            .withBody("THIS IS A ${newValue} TEST\n\n")
//                            )
//            );
//            givenThat(get(urlEqualTo("/hello"))
//                    .willReturn(
//                            aResponse()
//                                    .withHeader("content-type", "application/json")
//                                    .withBodyFile("com.tabcorp.qa.mock-hello.json")
//                    )
//            );
//            stubFor(get(urlEqualTo("/com.tabcorp.qa.mock-hello"))
//                    .willReturn(aResponse()
//                            .withHeader("Content-Type", "application/json")
//                            .withBodyFile("com.tabcorp.qa.mock-hello.json")
//                    )
//            );

        };

    }

    public static void main(String[] args) {

        SpringApplication.run(WiremockApplication.class, args);

    }
}
