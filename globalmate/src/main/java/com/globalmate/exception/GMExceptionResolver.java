package com.globalmate.exception;

import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.uitl.BeanMapUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class GMExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        String excepitonMsg = ExceptionParser.getExcepitonMsg(e);
        JsonResult fail = JsonResult.fail(excepitonMsg);
        Map<String, Object> map = BeanMapUtils.beanToMap(fail);
        return new ModelAndView(jsonView, map);
    }
}
