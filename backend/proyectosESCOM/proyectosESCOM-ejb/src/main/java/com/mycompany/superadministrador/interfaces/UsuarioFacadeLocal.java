package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.entity.Usuario;
import java.util.List;
import javax.ejb.Local;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.TipoDocumento;
import java.util.Date;
/**
 * Esta es la interfaz para la clase usuario
 * Contiene todos los metodos requeridos para la entidad usuario
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();

    public List<Usuario> consultaLogin(String correo, String contrasena);

    public String[] consultarActividadesUsuario(int idUsuario);
    
    public String[] consultarTodasActividades(int idUsuario);

    public int editarToken(String token, int idUsuario);
    
    public int editarTokenRecuperarContrasena(String token, int idUsuario);

    public UsuarioPOJO busquedaToken(String firma);
    
    public UsuarioPOJO busquedaTokenRecuperar(String firma);

    public int editarTokenCerrarSesion(String firma, String correo);

    public List<Usuario> consultaDatosExistentes(String correo, int idDocumento);

    public void registrarUsuario(UsuarioPOJO usuario);
    
    public List<UsuarioPOJO> listarUsuarios(int cantidadDatos,int paginaActual);
    
    public List<UsuarioPOJO> listarUsuariosSinSuper(int cantidadDatos,int paginaActual,String correoElectronico);
    
    public List<UsuarioPOJO> filtrarUsuariosSuper(String palabraBusqueda, int cantidadDatos, int paginaActual);
    
    public List<UsuarioPOJO> filtrarUsuarios(String palabraBusqueda, String correo, int cantidadDatos, int paginaActual);
    
    public int filtrarUsuariosSuperCantidad(String palabraBusqueda);
    
    public int filtrarUsuariosCantidad(String palabraBusqueda,String correo);
            
    public UsuarioPOJO buscarUsuarioEspecifico(int cedula);
    
    public int editarUsuario(int cedula, UsuarioPOJO usuarioEditar,TipoDocumento tipo);
    
    public void cambiarEstadoUsuario(int cedula, String estado);
    
    public List<UsuarioPOJO> listarUsuariosModuloDocumental();
     
    public List<UsuarioPOJO> buscarUsuarioBitacora(String palabraBusqueda);
    
    public List<UsuarioPOJO> buscarUsuarioBitacoraDocumento(int documentoBusqueda);
    
    public UsuarioPOJO buscarUsuarioBitacoraId(int idUsuario);
    
    public void cambiarClaveInterna(String nuevaClave, UsuarioPOJO usuario);
    
    public List<UsuarioPOJO> buscarCorreoUsuario(String correo);
    
    public void cambiarNumeroIntentos(int numeroIntentos, int idUsuario);
    
    public void cambiarFechaIngreso(Date fechaIngreso, UsuarioPOJO usuario);
    
    public void cambiarIntentosConFecha(Usuario usuario);
}
