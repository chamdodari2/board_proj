package board_proj.action;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ContentDisposition;

import board_proj.dto.ActionForward;

public class BoardFileDownProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)  {
		//	<a href="boardFileDownPro.do?downFile=${article.board_file }"> ${article.board_file}</a>
		try {
		System.out.println("일단 다운로드 페이지로 넘어왔당");		
		String downFile = request.getParameter("downFile");
		System.out.println(downFile);
		
		String savePath ="upload";
		ServletContext context = request.getServletContext();
		
		System.out.println(context);
		String sDownloadPath  = context.getRealPath(savePath);
		String sFilePath = sDownloadPath +"\\" + downFile;
		byte b[] = new byte[4096];
		FileInputStream in =new FileInputStream(sFilePath);
		String sMimeType = request.getServletContext().getMimeType(sFilePath); //requewst 빼야하는거아잉가
		System.out.println("sMimeType >>>" + sMimeType);
		
		if (sMimeType ==null)
			sMimeType = "application/octet-stream";
		response.setContentType(sMimeType);
		String agent = request.getHeader("User-Agent");
		boolean ieBrowser = (agent.indexOf("MSIE") > -1 )|| (agent.indexOf("Trident") >-1);
		
		if (ieBrowser) {
			downFile = URLEncoder.encode(downFile,"UTF-8").replace("\\+", "%20");
		}else {
			downFile =new String(downFile.getBytes("UTF-8"),"iso-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; downFile= " +downFile);
		
		ServletOutputStream out2 = response.getOutputStream();
		int numRead;
		
		while ((numRead = in.read(b,0,b.length)) != -1) {
			out2.write(b,0,numRead);
		}
		out2.flush();
		out2.close();
		in.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
