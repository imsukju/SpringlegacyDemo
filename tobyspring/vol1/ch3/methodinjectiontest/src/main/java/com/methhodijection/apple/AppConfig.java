package com.methhodijection.apple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Spring Configuration class
@Configuration
@ComponentScan("com.methhodijection.apple")
class AppConfig {

@Bean
public Command command() {
   return new ConcreteCommand();
}

@Bean
public CommandManager commandManager() {
   return new CommandManager();
}
}