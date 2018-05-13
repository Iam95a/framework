package com.chen.framework;

import com.chen.framework.bean.Data;
import com.chen.framework.bean.Handler;
import com.chen.framework.bean.Param;
import com.chen.framework.bean.View;
import com.chen.framework.helper.BeanHelper;
import com.chen.framework.helper.ConfigHelper;
import com.chen.framework.helper.ControllerHelper;
import com.chen.framework.util.ReflectionUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod();
        String requestPath = req.getPathInfo();

        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass=handler.getControllerClass();
            Object controllerBean= BeanHelper.getBean(controllerClass);
            Map<String ,Object> paramMap=new HashMap<>();
            Enumeration<String> paramNames=req.getParameterNames();
            while(paramNames.hasMoreElements()){
                String paramName=paramNames.nextElement();
                String paramValue=req.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            //TODO http请求体中的参数暂时不管
            Param param=new Param(paramMap);
            Method reqMethod=handler.getRequestMethod();
            Object result= ReflectionUtil.invokeMethod(controllerBean,
                    reqMethod,param);
            if(result instanceof View){
                View view=(View) result;
                String path=view.getPath();
                if(StringUtils.isNotEmpty(path)){
                    if(path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() + path);
                    }else{
                        Map<String ,Object> model=view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            req.setAttribute(entry.getKey(),entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);

                    }
                }else if(result instanceof Data){
                    Data data=(Data) result;
                    Object model=data.getModel();
                    if(model!=null){
                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("utf8");
                        PrintWriter writer=resp.getWriter();
                        String json=new Gson().toJson(model);
                        writer.print(json);
                        writer.flush();
                        writer.close();
                    }
                }
            }

        }
    }
}
