package actions;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.UsuarioDAO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import model.Viajero;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ImageAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	byte[] imageInByte = null;
	String id;

	private HttpServletRequest servletRequest;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public ImageAction() {		
	}

	public String execute() {
		return SUCCESS;
	}

	public byte[] getCustomImageInBytes() throws NumberFormatException, Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			UsuarioDAO udao = FactoryDAO.getUsuarioDAO();
			Viajero v = (Viajero) udao.encontrar(Integer.parseInt(request.getParameter("id")));
			InputStream in = new ByteArrayInputStream(v.getFotoPerfil());
			BufferedImage bImageFromConvert = ImageIO.read(in);
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bImageFromConvert, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imageInByte;
	}

	private File getImageFile(String id) {
		String filePath = servletRequest.getSession().getServletContext()
				.getRealPath("/");
		File file = new File(filePath + "/Image/", id);
		System.out.println(file.toString());
		return file;
	}

	public String getCustomContentType() {
		return "image/jpeg";
	}

	public String getCustomContentDisposition() {
		return "anyname.jpg";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;

	}

}