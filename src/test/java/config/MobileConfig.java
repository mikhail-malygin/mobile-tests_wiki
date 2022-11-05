package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${host}.properties"
})
public interface MobileConfig extends Config{

    @Key("device")
    @DefaultValue("Galaxy Tab S8")
    String device();

    @Key("osVersion")
    @DefaultValue("12.0")
    String osVersion();
}
