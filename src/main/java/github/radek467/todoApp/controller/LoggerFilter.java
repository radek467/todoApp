package github.radek467.todoApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
class LoggerFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest){
            logger.info("log " + ((HttpServletRequest) servletRequest).getMethod() + " " + ((HttpServletRequest) servletRequest).getRequestURI());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
