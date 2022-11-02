package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:realDevice.properties"
})
public interface RealDeviceConfig extends Config {

    @Key("device_name")
    String deviceName();

    @Key("platform_name")
    String platformName();

    @Key("platform_version")
    String platformVersion();

    @Key("appium_URL")
    String appiumUrl();

}
