/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w3.ioc;

import com.google.inject.AbstractModule;
import ua.itstep.hw_w3.services.config.ConfigService;
import ua.itstep.hw_w3.services.config.JsonConfigService;
import ua.itstep.hw_w3.services.hash.HashService;
import ua.itstep.hw_w3.services.hash.Md5HashService;
import ua.itstep.hw_w3.services.kdf.KdfService;
import ua.itstep.hw_w3.services.kdf.PbKdf1Service;
import ua.itstep.hw_w3.services.timestamp.TimestampService;
import ua.itstep.hw_w3.services.timestamp.UnixSecondsTimestampService;

public class ServicesConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(KdfService.class).to(PbKdf1Service.class);
        bind(HashService.class).to(Md5HashService.class);
        bind(TimestampService.class).to(UnixSecondsTimestampService.class);
        bind(ConfigService.class).to(JsonConfigService.class)
                        .asEagerSingleton();
    }
}
