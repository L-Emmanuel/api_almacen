package com.almacen.api;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlmacenApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlmacenApplication.class, args);
	}

}
