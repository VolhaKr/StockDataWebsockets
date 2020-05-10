package org.examples.volha.stock.webpresentation;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
//@WebFilter (urlPatterns = "/api/count")
public class ConnectFilter implements Filter {
    //  private static final Logger logger = LoggerFactory.getLogger(ConnectFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter works");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

//            logger.info("filter:" + ((HttpServletRequest) servletRequest).getRequestURL());
//            filterChain.doFilter(servletRequest, servletResponse);
//   }

    @Override
    public void destroy() {
    }


}