package com.sky.server.mvc.controller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * Created by jcooky on 2014. 6. 25..
 */
@RestController
@RequestMapping("/download")
public class DownloadController {
  @Autowired
  private ServletContext servletContext;

  @RequestMapping(value = "/config/{projectId}/{workId}", produces = "text/xml")
  public String downloadConfig(@PathVariable long projectId, @PathVariable long workId) throws IOException {

    String str = IOUtils.toString(ClassLoader.getSystemResourceAsStream("jrat.xml"));
    return StringUtils.replaceEach(str, new String [] {"${projectId}", "${workId}"},
        new String[] {Long.toString(projectId), Long.toString(workId)});
  }
}