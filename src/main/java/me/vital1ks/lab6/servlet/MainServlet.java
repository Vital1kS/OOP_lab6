package me.vital1ks.lab6.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.vital1ks.lab6.handlers.DotHandler;

@WebServlet
public class MainServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("randomButton")!=null)
			DotHandler.generateRandomDots();
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html");
		Dimension dimension = new Dimension(600,600);
		BufferedImage bufferedImage = new BufferedImage(dimension.width,dimension.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2d = bufferedImage.createGraphics();
		File file = new File("C:\\Users\\Vitalya\\eclipse-workspace\\lab6\\src\\main\\java\\me\\vital1ks\\lab6\\resources\\image.png");
		
		float b = Float.parseFloat(req.getParameter("bText")!=null?req.getParameter("bText"):"0");
		float k = Float.parseFloat(req.getParameter("kText")!=null?req.getParameter("kText"):"1");
		float max_range = Float.parseFloat(req.getParameter("maxRangeText")!=null?req.getParameter("maxRangeText"):"1000");
		int x1 = 0;
		int y1 = dimension.height-(int)b;
		int y2 = 0;
		int x2 = (int)((dimension.height-b)/k);
		
		
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, dimension.width,dimension.height);
		graphics2d.setColor(Color.BLACK);
		BasicStroke basicStroke = new BasicStroke(2);
		graphics2d.setStroke(basicStroke);
		graphics2d.drawLine(x1, y1, x2, y2);
		//DotHandler.generateRandomDots();
		graphics2d.setColor(Color.ORANGE);
		ArrayList<int[]> dots = DotHandler.getDots();
		for (int[] dot : dots) {
			int x0 = dot[0];
			int y0 = dot[1];
			double distance = (Math.abs((y2-y1)*x0-(x2-x1)*y0+x2*y1-y2*x1)/Math.sqrt(Math.pow(y2-y1, 2)+Math.pow(x2-x1, 2)));
			if(distance <= max_range)
				graphics2d.fillOval(x0, y0, 12, 12);
		}
		
		ImageIO.write(bufferedImage, "png", file);
		out.print("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "   <meta charset=\"UTF-8\">\r\n"
				+ "   <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "   <title>Document</title>\r\n"
				+ "   \r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "   <form action=\"main.html\" method=\"get\">\r\n"
				+ "      <input type=\"number\" step=\"0.01\" placeholder=\"k\" name=\"kText\" value=\""+k+"\"/>\r\n"
				+ "      <input type=\"number\" step=\\\"0.01\\\" placeholder=\"b\" name=\"bText\" value=\""+b+"\"/>\r\n"
				+ "      <input type=\"number\" step=\\\"0.01\\\" placeholder=\"max range\" name=\"maxRangeText\" value=\""+max_range+"\"/>\r\n"
				+ "      <input type=\"submit\" value=\"Ok\" name=\"okButton\"/>\r\n"
				+ "      <input type=\"submit\" value=\"Random\" name=\"randomButton\"/><br>\r\n"
				+ "  </form>  \r\n"
				+ "  <img alt=\"graph\" src=\"http://localhost:25565/lab6/image\">\r\n"
				+ "   <form action=\"random\" method=\"get\">\r\n"
				+ "  </form>  \r\n"
				+ "</body>\r\n"
				+ "</html>");
		out.close();
	}

}
