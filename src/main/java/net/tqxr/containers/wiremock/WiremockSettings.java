package net.tqxr.containers.wiremock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WiremockSettings {

    @Value("${wiremock.rootDirectory}")
    String rootDirectory;


}
