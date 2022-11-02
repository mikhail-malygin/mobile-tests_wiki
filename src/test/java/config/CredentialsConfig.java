package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.properties")
public interface CredentialsConfig extends Config {
    @Key("user")
    String user();

    @Key("key")
    String key();

    @Key("url")
    String url();

    @Key("app")
    String app();
}
