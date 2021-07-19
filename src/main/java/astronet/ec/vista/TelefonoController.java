package astronet.ec.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.Telefono;
import astronet.ec.on.ClienteON;
import astronet.ec.on.TelefonoON;

@ManagedBean
@ViewScoped
public class TelefonoController implements Serializable {

	// private static final long serialVersionUID = 8799656478674716638L;
	private static final long serialVersionUID = 1L;
	private Cliente cliente;

	private List<Telefono> telefonos;
	/**
	 * Declaraacion de variables
	 */
	private String cedula;

	private Telefono nuevoTelefono;
	private String nuevoNumero;
	private String nuevoTipoTelefono;
	private String numeroActualizar;

	/**
	 * Fin de la declaracion
	 */



	/**
	 * Inyeccion de las clases ON
	 */
	@Inject
	private TelefonoON telOn;
	
	@Inject
	private ClienteON clion;
	



	/**
	 * Fin de la inyeccion
	 */

	/**
	 * Creacion del Constructor
	 */

	@PostConstruct
	public void init() {
		telefonos = new ArrayList<Telefono>();

	}



	public String editar(int codigo) {

		return "editarClientes?faces-redirect=true&id=" + codigo;
	}

	public String editarRegistro(int codigo) {
		return "agendamiento?faces-redirect=true&id=" + codigo;
	}
	
	




	public String getNuevoNumero() {
		return nuevoNumero;
	}








	public void setNuevoNumero(String nuevoNumero) {
		this.nuevoNumero = nuevoNumero;
	}








	


	
	
	//Fin de getter y setters



	public String getNumeroActualizar() {
		return numeroActualizar;
	}



	public void setNumeroActualizar(String numeroActualizar) {
		this.numeroActualizar = numeroActualizar;
	}



	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}



	public Telefono getNuevoTelefono() {
		return nuevoTelefono;
	}



	public void setNuevoTelefono(Telefono nuevoTelefono) {
		this.nuevoTelefono = nuevoTelefono;
	}



	public String getNuevoTipoTelefono() {
		return nuevoTipoTelefono;
	}



	public void setNuevoTipoTelefono(String nuevoTipoTelefono) {
		this.nuevoTipoTelefono = nuevoTipoTelefono;
	}

	
	


	public List<Telefono> getTelefonos() {
		return telefonos;
	}



	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	
	
	public void editTelefono(int id,String numero) {

		try {
			System.out.println("NUMERO ACTUALIZADO");
			
			System.out.println("TELEFONO A ACTUALIZAR : ID= -> "+ id);
			System.out.println("TELEFONO A ACTUALIZAR : NUMERO ANTIGUO-> "+ numero);
			
			Telefono telActualizar=telOn.read(id);
			telActualizar.setTelNumero(numero);
			telOn.updateTelefono(telActualizar);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se actualizo el telefono correctamente"));
			
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se pudo actualizar el telefono"));

		}
		
	
	}

	
	
	
	public String newTelefono() {
		try {
	
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String action = params.get("action");
			
			System.out.println("this is id as parameter "+action);
			nuevoTelefono=new Telefono(nuevoNumero,nuevoTipoTelefono,clion.getClienteCedula(action));
			
			//System.out.println(nuevoTelefono.getCliente().getApellidos());
			
			System.out.println("valeverga gaviel");
				telOn.guardar(nuevoTelefono);	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Telefono Agregado Correctamente"));
		
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se pudo agregar el telefono"));

		}
		
	
		return null;
		}
	
	

	public String deleteTelefono(int id) {
		
		try {
			
			telOn.delete(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Telefono Eliminado Correctamente"));

			
		}catch (Exception e) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se pudo eliminar el telefono"));

		}
		return null;
		
	}
	
		
		
	}
