package com.ruan.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

@Slf4j
@Component
public class RuanPostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        try {
            RequestContext requestContext = RequestContext.getCurrentContext();
            long startTime = (long) requestContext.get("startTime");
            long endTime = System.currentTimeMillis();
            long cost = endTime - startTime;
            InputStream responseDataStream = requestContext.getResponseDataStream();
            String body = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
            if (body == null || "".equals(body)) {
                body = requestContext.getResponseBody();
            }
            log.info("耗时：{}ms，response：{}", cost, body);
            // 流读取后需要重新设置内容
            requestContext.setResponseBody(body);
        } catch (IOException e) {
            e.printStackTrace();
            rethrowRuntimeException(e);
        }
        return null;
    }
}
