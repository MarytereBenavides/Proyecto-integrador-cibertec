package edu.cibertec.proyecto.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cibertec.proyecto.entity.Capsula_producto;
import edu.cibertec.proyecto.entity.Capsula_proveedor;
import edu.cibertec.proyecto.entity.Capsula_cliente;
import edu.cibertec.proyecto.servicios.Serv_productos;
import edu.cibertec.proyecto.servicios.Serv_proveedores;
import edu.cibertec.proyecto.servicios.Serv_clientes;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {
    
    @Autowired
    private Serv_productos SProducto;
    
    @Autowired
    private Serv_proveedores SProveedor;
    
    @Autowired
    private Serv_clientes SCliente;
    
    @GetMapping(value = "/productos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Capsula_producto> listarProductos() {
    	 List<Capsula_producto> productos = SProducto.listar();

         // Si la lista es muy grande, limitarla a los primeros 10
         if (productos.size() > 10) {
             return productos.subList(0, 10);
         }

         return productos;
    }
    
    @GetMapping(value = "/proveedores", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Capsula_proveedor> listarProveedores() {
    	  List<Capsula_proveedor> proveedores = SProveedor.listar();
          if (proveedores.size() > 10) {
              return proveedores.subList(0, 10);
          }
          return proveedores;
    }
    
    @GetMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Capsula_cliente> listarClientes() {
    	   List<Capsula_cliente> clientes = SCliente.listar();
           if (clientes.size() > 10) {
               return clientes.subList(0, 10);
           }
           return clientes;
    }
    
    // Endpoint específico para reposiciones (productos con bajo stock)
    @GetMapping(value = "/reposiciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Capsula_producto> listarReposiciones() {
    	 List<Capsula_producto> productos = SProducto.listar();
    	 List<Capsula_producto> productosReponer = new ArrayList<>();
    	 for (Capsula_producto producto : productos) {
    	        if (producto.getStock() < producto.getStock_min()) {
    	            producto.setReponer(true); // Marcamos el producto como "para reponer"
    	            productosReponer.add(producto);
    	        }
    	    }
    	 if (productosReponer.size() > 10) {
    	        return productosReponer.subList(0, 10);
    	    }
    	   return productosReponer; 
    }
}