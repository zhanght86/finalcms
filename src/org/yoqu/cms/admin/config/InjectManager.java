package org.yoqu.cms.admin.config;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import org.yoqu.cms.core.config.SystemVariable;
import org.yoqu.cms.core.aop.InvokeBefore;
import org.yoqu.cms.core.config.Constant;
import org.yoqu.cms.core.util.FinalProxy;
import org.yoqu.cms.core.aop.SiteTitle;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by yoqu on 2016/4/4 .
 */
public class InjectManager {
    private static final Log log = Log.getLog(InjectManager.class);
    private static InjectManager injectManager = null;

    public static InjectManager getInstance() {
        if (injectManager == null) {
            synchronized (InjectManager.class) {
                injectManager = (InjectManager) new FinalProxy().createProxy(InjectManager.class);
                injectManager.init();
            }
        }
        return injectManager;
    }

    private void init() {
        SystemVariable.init();//初始化参数
    }

    @InvokeBefore("PageInject_Before")
    public void injectCommonVariable(Controller controller) {
        injectSystemVariable(controller);//inject System Variable..
        injectOtherVariable(controller);
    }

    public void injectPersonalVariable(Controller controller) {
        injectOnlineUser(controller);//inject system Online User variable..
    }

    private void injectOtherVariable(Controller controller) {
        //inject site url.
        StringBuffer url = controller.getRequest().getRequestURL();
        String tempContextUrl = url.delete(url.length() - controller.getRequest().getRequestURI().length(), url.length()).append(controller.getRequest().getServletContext().getContextPath()).append("/").toString();
        controller.setAttr(Constant.SITE_URL, tempContextUrl);
    }

    /**
     * inject system variable.
     *
     * @param controller
     */
    private void injectSystemVariable(Controller controller) {

        for (HashMap.Entry<String, String> item : SystemVariable.systemConstant.entrySet()) {
            controller.setAttr(item.getKey().trim(), item.getValue().trim());
        }
    }

    private void injectOnlineUser(Controller controller) {
        controller.setAttr(Constant.ONLINE_USER, controller.getSessionAttr(Constant.ONLINE_USER));
    }

    public void injectAnnotation(Method method, Controller controller) {
        if (method.isAnnotationPresent(SiteTitle.class)) {
            SiteTitle siteTitle = method.getAnnotation(SiteTitle.class);
            controller.setAttr(Constant.SITE_TITLE, siteTitle.value());
        }
    }

}