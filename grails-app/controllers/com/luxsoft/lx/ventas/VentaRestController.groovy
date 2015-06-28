package com.luxsoft.lx.ventas

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured

//@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class VentaRestController extends RestfulController{

	static responseFormats=["json"]

	VentaRestController(){
		super(Venta)
	}
    
}
