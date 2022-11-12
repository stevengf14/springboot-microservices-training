package ec.learning.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 *
 * @author Steven Guamán - November 2022
 */
@Component
public class PostTimeElapsedFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PostTimeElapsedFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info("Entering post");

		Long initTime = (Long) request.getAttribute("initTime");
		Long endTime = System.currentTimeMillis();
		Long elapsedTime = endTime - initTime;

		log.info(String.format("Elapsed time in seconds %s seg.", elapsedTime.doubleValue() / 1000.00));
		log.info(String.format("Elapsed time in miliseconds %s ms.", elapsedTime));
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
