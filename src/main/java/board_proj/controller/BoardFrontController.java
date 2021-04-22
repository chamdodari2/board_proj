package board_proj.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.NullAction;
import board_proj.dto.ActionForward;

@WebServlet(urlPatterns = {"*.do"},  // 모든 확장자가 do인 애들이 부르면 각 요청에 맞는 jsp로 포워딩쓰           //1
		 loadOnStartup = 1, 		//무조건 제일 처음에 로드하라는 뜻(화면에 제일먼저! 필터보다?)
			initParams = {
					@WebInitParam(
							name="configFile", 
							value="/WEB-INF/commandAction.properties")
			}
		)
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Action> actionMap = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {  //2
		String configFile = config.getInitParameter("configFile");
		try(InputStream is = config.getServletContext().getResourceAsStream(configFile)){  //4
			Properties props = new Properties();
			props.load(is);
			
			for(Entry<Object, Object> entry : props.entrySet()) {
				
				Class<?> cls;
				Action action =null;
				try {												//6 . 트라이캐치로 묶었기 때문에 commandAction.properties 등에서 오류 있어도 각각 어디에서 에러난지 알수있다
				cls = Class.forName((String)entry.getValue());    //5. 이 두줄로 끝난당
				action = (Action) cls.newInstance();
				}catch(ClassNotFoundException e ) {
					
						action = new NullAction();
					
					e.printStackTrace(); //보안을 위해서는 런칭할떄 주석처리 꼭 해주어야 해킹안당한다! 해커가 이거가지고 해킹한당
				}
				actionMap.put((String)entry.getKey(), action);
			}

		} catch (IOException |  InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getServletPath();
		
		Action action = actionMap.get(command);
		
		
		ActionForward forward = action.execute(request, response);

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
		
	}

}