package org.pab.webapp.servlet.services;

import org.pab.webapp.servlet.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{

    @Override

    public List<Producto> listar(){
        return Arrays.asList(
                new Producto(1L,"Cafe","Bebida",150),
                new Producto(2L,"Alfajor","Comida",200),
                new Producto(3L,"Oreo","Comida",120),
                new Producto(4L,"Pepsi","Bebida",160));
    }

}
