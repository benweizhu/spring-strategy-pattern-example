package me.zeph.spring.strategy.factory;

import me.zeph.spring.strategy.model.Car;
import org.springframework.stereotype.Component;

@Component
public class HondaCarFactory implements CarFactory {

  @Override
  public Car build() {
    return Car.builder().name("Honda").build();
  }
}
