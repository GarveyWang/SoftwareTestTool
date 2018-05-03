package com.softwaretest.tool.controller;

import com.softwaretest.tool.ToolApplication;
import com.softwaretest.tool.dto.ClassDto;
import com.softwaretest.tool.dto.ResultDto;
import com.softwaretest.tool.service.ClassUtil;
import com.softwaretest.tool.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {
    private final String tempClasspath="G:\\code\\SoftwareTestTool\\tempClasspath";

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/")
    public String home(){
        return "/index";
    }

    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    @ResponseBody
    public ClassDto compile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        File localFile = new File(tempClasspath,fileName);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String clsName = fileName.split("\\.")[0];
        System.out.println(localFile.getPath());
        Class cls = ClassUtil.loadClass(localFile.getPath(),clsName);
        ToolApplication.currentClass = cls;
        ClassDto dto = new ClassDto(cls);
        System.out.println(ToolApplication.currentClass.toString());
        return dto;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto test(@RequestParam String signature){
        return testService.test(signature);
    }

    @RequestMapping(value = "/uploadcsv", method = RequestMethod.POST)
    @ResponseBody
    public void uploadcsv(MultipartFile file){
        String fileName = file.getOriginalFilename();
        File localFile = new File(tempClasspath,fileName);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ToolApplication.csv = localFile;
    }


}