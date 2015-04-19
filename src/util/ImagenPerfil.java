package util;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.UsuarioDAO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Viajero;

public class ImagenPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void init(){
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImagenPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		try {
			
			UsuarioDAO udao = FactoryDAO.getUsuarioDAO();
			Viajero v = (Viajero)udao.encontrar(1);
			InputStream in = new ByteArrayInputStream(v.getFotoPerfil());
			BufferedImage bImageFromConvert = ImageIO.read(in);			
			ImageIO.write(bImageFromConvert, "jpg", response.getOutputStream());
		} catch (IOException ioe) {
			System.out.println("ERROR " + ioe);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}