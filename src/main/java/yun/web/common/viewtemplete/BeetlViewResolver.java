package yun.web.common.viewtemplete;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

/**
 * Created by larry on 16/3/2.
 */
public class BeetlViewResolver extends AbstractCachingViewResolver {

    Logger logger = Logger.getLogger(BeetlViewResolver.class);

    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        return null;
    }
}
