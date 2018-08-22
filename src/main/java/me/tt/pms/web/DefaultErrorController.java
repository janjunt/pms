package me.tt.pms.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DefaultErrorController
 * @Description: 默认错误处理前端控制器
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/8 17:28
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class DefaultErrorController  extends AbstractErrorController {
    @Value("${server.error.path:${error.path:/error}}")
    public static final String ERROR_PATH = "/error";

    public DefaultErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }


    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, false));
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);

        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
    }

    @RequestMapping
    @ResponseBody
    public AjaxResult error(HttpServletRequest request) {
        Map<String, Object> model = getErrorAttributes(request, false);
        String message = model.getOrDefault("message", "服务器发生错误").toString();

        return AjaxResult.error(message);
    }
}