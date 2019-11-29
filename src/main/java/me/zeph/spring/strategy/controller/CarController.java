package me.zeph.spring.strategy.controller;

import me.zeph.spring.strategy.factory.CarFactory;
import me.zeph.spring.strategy.factory.HondaCarFactory;
import me.zeph.spring.strategy.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CarController {

  @Autowired
  private List<CarFactory> carFactories;

  private Map<String, Class> factoryMapping = new HashMap<>();

  public CarController() {
    factoryMapping.put("Honda", HondaCarFactory.class);
  }

  @GetMapping("/cars/{name}")
  public Car getCarByName(@PathVariable String name) {
    return getCarFactory(name).build();
  }

  private CarFactory getCarFactory(@PathVariable String name) {
    return carFactories.stream()
        .filter(carFactory -> carFactory.getClass().equals(factoryMapping.get(name)))
        .collect(Collectors.toList()).get(0);
  }
}
