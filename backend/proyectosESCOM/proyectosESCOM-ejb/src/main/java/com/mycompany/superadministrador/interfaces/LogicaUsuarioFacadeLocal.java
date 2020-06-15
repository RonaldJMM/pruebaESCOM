package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.ClavePOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;

/**
 * Esta es la interfaz para la clase logica usuario Contiene todos los metodos
 * requeridos para la conexion de la logica con la entidad usuario
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Local
public interface LogicaUsuarioFacadeLocal {

    public UsuarioPOJO loginUsuario(String correo, String contrasena) throws ExcepcionGenerica;

    public void cerrarSesion(String token) throws ExcepcionGenerica;

    public UsuarioPOJO devolverDatosUsuario(String token);

    public void registrarUsuario(UsuarioPOJO usuario) throws ExcepcionGenerica;

    public List<UsuarioPOJO> devolverUsuarios(String token, int cantidadDatos, int paginaActual) throws ExcepcionGenerica;

    public List<UsuarioPOJO> devolverUsuariosFiltrados(String palabraBusqueda, String token, int cantidadDatos, int paginaActual) throws ExcepcionGenerica;

    public int cantidadDeDatosFiltrados(String token, String palabraBusqueda) throws ExcepcionGenerica;
    
    public int cantidadDeDatos(String token) throws ExcepcionGenerica;

    public List<TipoDocumentoPOJO> devolverDocumentos() throws ExcepcionGenerica;

    public UsuarioPOJO traerUsuarioCedula(int cedula) throws ExcepcionGenerica;

    public void editarUsuario(int cedula, UsuarioPOJO usuarioEditar) throws ExcepcionGenerica;

    public void cambiarEstadoUsuario(int cedula, DatosSolicitudPOJO datosSolicitud) throws ExcepcionGenerica;

    public List<ActividadPOJO> listarActividadesUsuario(int cedula) throws ExcepcionGenerica;

    public List<ActividadPOJO> listarActividadesNoAsociadasUsuario(int numeroDocumento, int idModulo) throws ExcepcionGenerica;

    public void eliminarActividadUsuario(int cedula, List<ActividadPOJO> listaActividad) throws ExcepcionGenerica;

    public void asignarActividadAUsuario(int numeroDocumento, ActividadPOJO actividad) throws ExcepcionGenerica;

    public List<ActividadPOJO> listarActividadesUsuarioActivas(int cedula) throws ExcepcionGenerica;

    public List<ModuloPOJO> redireccionUsuario(String token) throws ExcepcionGenerica;

    public List<UsuarioPOJO> devolverUsuariosModuloDocumental();

    public void cambiarClaveInterna(ClavePOJO clavePOJO) throws ExcepcionGenerica;

    public String devolverCorreo(String token) throws ExcepcionGenerica;

    public String cambiarContrasenaExterna(String nuevaContrasena, String token, DatosSolicitudPOJO datos) throws ExcepcionGenerica;

    public String generarTokenRecuperarContrasena(String correoElectronico) throws ExcepcionGenerica;

    public String validarTokenRecuperarContrasena(String token) throws ExcepcionGenerica;

}
