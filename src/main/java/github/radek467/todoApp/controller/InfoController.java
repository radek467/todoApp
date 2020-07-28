package github.radek467.todoApp.controller;

import ch.qos.logback.classic.spi.TurboFilterList;
import github.radek467.todoApp.TaskConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController()
class InfoController {

    DataSourceProperties dataSource;
    TaskConfigurationProperties multipleTask;

    public InfoController(DataSourceProperties dataSource, TaskConfigurationProperties multipleTask) {
        this.dataSource = dataSource;
        this.multipleTask = multipleTask;
    }

    @GetMapping("/url")
    String url(){
        return dataSource.getUrl();
    }

    @GetMapping("/prop")
    boolean isMultipleTask(){
        return multipleTask.getTemplate().isAllowMultipleTask();
    }
}
