package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.entity.Modulo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Este es el bean de la entidad modulo Contiene todos los metodos para la
 * persistencia y consultas a la base de datos
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Stateless
public class ModuloFacade extends AbstractFacade<Modulo> implements ModuloFacadeLocal {

    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloFacade() {
        super(Modulo.class);
    }

    /**
     * Metodo que realiza la consulta de datos de modulo segun el nombre
     *
     * @param nombreModulo
     * @return
     *
     */
    @Override
    public List<Modulo> consultaDatosExistentes(String nombreModulo) {
        TypedQuery<Modulo> consultaDatosM = em.createNamedQuery("consultarExistenciaModulo", Modulo.class);
        consultaDatosM.setParameter("nombreModulo", nombreModulo.toLowerCase());

        return consultaDatosM.getResultList();
    }

    @Override
    public List<Modulo> consultaDatosExistentesUrlModulo(String urlModulo) {

        TypedQuery<Modulo> consultaDatosM = em.createNamedQuery("consultarExistenciaUrl", Modulo.class);
        consultaDatosM.setParameter("urlModulo", urlModulo.toLowerCase());
        return consultaDatosM.getResultList();
    }

    /**
     * Metodo que realiza la consulta de acronimo
     *
     * @param acronimo
     * @return
     *
     */
    @Override
    public List<Modulo> consultaAcronimo(String acronimo) {
        TypedQuery<Modulo> consultaAcronimo = em.createNamedQuery("consultarAcronimo", Modulo.class);
        consultaAcronimo.setParameter("acronimo", acronimo);

        return consultaAcronimo.getResultList();
    }

    /**
     * Metodo que realiza la consulta para registrar modulo
     *
     * @param modulo
     * @param acronimo
     *
     */
    @Override
    public void registrarModulo(ModuloPOJO modulo, String acronimo) {

        Modulo moduloN = new Modulo(new Date(), "Activo", modulo.getImagenModulo(), modulo.getNombreModulo(),
                acronimo, modulo.getDescripcionModulo());
        moduloN.setUrl(modulo.getUrl());
        em.persist(moduloN);
    }

    /**
     * Metodo que realiza la consulta a la tabla modulo Devuelve una lista con
     * los modulos registrados
     *
     * @return 
       *
     */
    @Override
    public List<ModuloPOJO> listarModulos() {

        TypedQuery<Modulo> consultaModulosRegistrados = em.createNamedQuery("consultaModulos", Modulo.class);
        List<ModuloPOJO> listaModulos = new ArrayList<>();
        for (Modulo m : consultaModulosRegistrados.getResultList()) {
            ModuloPOJO modulo = new ModuloPOJO();
            modulo.setIdModulo(m.getIdModulo());
            modulo.setEstadoModulo(m.getEstado());
            modulo.setImagenModulo(m.getImagen());
            modulo.setNombreModulo(m.getNombreModulo());
            modulo.setDescripcionModulo(m.getDescripcionModulo());
            modulo.setUrl(m.getUrl());
            modulo.setAcronimo(m.getAcronimo());
            listaModulos.add(modulo);
        }
        return listaModulos;
    }

    /**
     * Metodo que realiza la consulta a la tabla modulo Devuelve los datos de un
     * modulo registrado con el id enviado
     *
     * @param idModulo
     * @return 
       *
     */
    @Override
    public ModuloPOJO buscarModuloEspecifico(int idModulo) {

        TypedQuery<Modulo> consultaModuloEsp = em.createNamedQuery("consultaModuloEsp", Modulo.class);
        consultaModuloEsp.setParameter("idModulo", idModulo);
        Modulo moduloEspDB = consultaModuloEsp.getSingleResult();
        ModuloPOJO moduloRespuesta = new ModuloPOJO();
        moduloRespuesta.setIdModulo(moduloEspDB.getIdModulo());
        moduloRespuesta.setImagenModulo(moduloEspDB.getImagen());
        moduloRespuesta.setNombreModulo(moduloEspDB.getNombreModulo());
        moduloRespuesta.setDescripcionModulo(moduloEspDB.getDescripcionModulo());
        moduloRespuesta.setUrl(moduloEspDB.getUrl());
        moduloRespuesta.setEstadoModulo(moduloEspDB.getEstado());
        return moduloRespuesta;
    }

    /**
     * Metodo que realiza la modificacion de un modulo Recibe id para filtrar la
     * busqueda
     *
     * @param idModulo
     * @param moduloEditar
     *
     *
     */
    @Override
    public void editarModulo(int idModulo, ModuloPOJO moduloEditar) {
        Modulo modulo = em.find(Modulo.class, idModulo);
        modulo.setImagen(moduloEditar.getImagenModulo());
        modulo.setNombreModulo(moduloEditar.getNombreModulo());
        modulo.setDescripcionModulo(moduloEditar.getDescripcionModulo());
        modulo.setUrl(moduloEditar.getUrl());
        em.merge(modulo);
    }

    /**
     * Metodo que realiza el cambio de estado de un modulo Recibe id para
     * filtrar la busqueda y el valor del estado
     *
     * @param idModulo
     * @param estado
     *
     *
     */
    @Override
    public void cambiarEstadoModulo(int idModulo, String estado) {

        Modulo modulo = em.find(Modulo.class, idModulo);
        modulo.setEstado(estado);
        em.merge(modulo);
    }

        /**
     * Metodo que realiza la consulta a la tabla modulo Devuelve los datos de un
     * modulo registrado para la bitacora
     *
     * @param palabraBusqueda
     * @return 
       *
     */
    @Override
    public List<ModuloPOJO> buscarModuloBitacora(String palabraBusqueda) {
        String busqueda = palabraBusqueda.toLowerCase();
        List<Modulo> lista = new ArrayList();
        TypedQuery<Modulo> moduloDB = em.createQuery("select m from Modulo m where Lower(m.nombreModulo)=:busqueda", Modulo.class);
        moduloDB.setParameter("busqueda", busqueda);
        lista = moduloDB.getResultList();
        List<ModuloPOJO> listaModulo = new ArrayList();
        for (Modulo m : lista) {
            ModuloPOJO moduloRespuesta = new ModuloPOJO();
            moduloRespuesta.setIdModulo(m.getIdModulo());
            moduloRespuesta.setNombreModulo(m.getNombreModulo());
            listaModulo.add(moduloRespuesta);
        }
        return listaModulo;
    }
    
    /**
     * Metodo que realiza la consulta a la tabla modulo Devuelve los datos de un
     * modulo registrado para la bitacora, recibe el id de modulo
     *
     * @param idModulo
     * @return
     *
     */
    @Override
    public ModuloPOJO buscarModuloBitacoraId(int idModulo) {

        Modulo lista = new Modulo();
        TypedQuery<Modulo> usuarioEspDB = em.createQuery("select m from Modulo m where m.idModulo =:idModulo", Modulo.class);
        usuarioEspDB.setParameter("idModulo", idModulo);
        lista = usuarioEspDB.getSingleResult();

        ModuloPOJO modulo = new ModuloPOJO();

        modulo.setNombreModulo(lista.getNombreModulo());
        modulo.setAcronimo(lista.getAcronimo());

        return modulo;
    }
}
