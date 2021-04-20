package board_proj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "UTF-8")
		})
public class CharacterEncodingFilter implements Filter {
	
	private String encoding;

	public void init(FilterConfig fConfig) throws ServletException { //얘가 제일먼저 호출이되야한당
		System.out.println("init filter");
		encoding = fConfig.getInitParameter("encoding");//이름이 encoding인 값을 가져와! ->UTF-8을 가져오게될거당
		if(encoding==null) { //내가 초기화하는거 까먹었으면 UTF-8로 해줭
			encoding="UTF-8";
		}
		
	}
	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding); 
		response.setCharacterEncoding(encoding); //여기서 추가작업 해줄거 아니고 필터링만 해줄거니까 contentType말고 이걸로 해줘도된당!!!
//		response.setContentType("text/html;charset=UTF-8");
		//response.setContentType("text/html; charset=" + encoding);
		chain.doFilter(request, response);
	}

	
	

}
