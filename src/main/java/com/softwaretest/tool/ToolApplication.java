package com.softwaretest.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan(basePackages = "com.softwaretest.tool.*")
@ServletComponentScan(basePackages = "com.softwaretest.tool.*")
public class ToolApplication {
	public static Class currentClass;
	public static File csv;
	public static void main(String[] args) {
		SpringApplication.run(ToolApplication.class, args);
	}
}
