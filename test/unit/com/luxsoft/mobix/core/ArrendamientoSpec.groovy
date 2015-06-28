package com.luxsoft.mobix.core

import grails.test.mixin.TestFor
import grails.test.mixin.*
import spock.lang.Specification
import spock.lang.Unroll
import grails.buildtestdata.mixin.Build
import com.luxsoft.lx.core.Producto
import com.luxsoft.utils.Periodo
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Arrendamiento)
@Mock([Arrendamiento,Renta])
@Build([Arrendamiento,Renta]) //Agregar Renta para activar la persistencia dinamica de la relacion one-to-many
class ArrendamientoSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Generar rentas mensuales"(){
        setup:
        def periodo=new Periodo('01/01/2015','31/12/2015')
        def arrendamiento=new Arrendamiento(
            tipo:'MENSUAL',
            inicio:periodo.fechaInicial,
            fin:periodo.fechaFinal,
            precio:1000.00
            )
        when:'Generamos rentas'
        def rentas=arrendamiento.generarRentas()

        then:'El numero de rentas es correcto'
        rentas.size()==12
        rentas.each{
            it.folio
        }
        rentas.find{it.folio==6}.inicio.format('dd/MM/yyyy')=='01/06/2015'
    }

    @Unroll
    void "El numero de rentas MENSUALES para el periodo #periodo debe ser #size"(){
        setup:
        def arrendamiento=new Arrendamiento(
            tipo:'MENSUAL',
            inicio:periodo.fechaInicial,
            fin:periodo.fechaFinal,
            precio:1000.00
            )
        expect:
        arrendamiento.generarRentas().size()==size

        where:
        periodo||size
        new Periodo('01/01/2015','31/12/2015')|12
        new Periodo('01/01/2015','30/06/2015')|6
        new Periodo('01/03/2015','31/08/2015')|6
        new Periodo('01/11/2015','31/03/2016')|5
    }

    @Unroll
    void "El numero de rentas BIMESTRAL para el periodo #periodo debe ser #size"(){
        setup:
        def arrendamiento=new Arrendamiento(
            tipo:'BIMESTRAL',
            inicio:periodo.fechaInicial,
            fin:periodo.fechaFinal,
            precio:1000.00
            )
        expect:
        def rentas=arrendamiento.generarRentas()
        rentas.size()==size
        rentas.each{renta ->
            println "$renta ${renta.getPeriodo().toMeses().size()}"
            assert renta.getPeriodo().toMeses().size()==2
            renta.getPeriodo().toMeses().size()==2
        }


        where:
        periodo||size
        new Periodo('01/01/2015','31/12/2015')|6
        new Periodo('01/01/2015','30/06/2015')|3
        new Periodo('01/03/2015','31/08/2015')|3
        new Periodo('01/11/2015','31/03/2016')|2
    }

    void "Alta de Arrendamiento"() {
    	setup:
    	def prod=Producto.build(clave:'RENTA_MENSUAL')
    	def a=Arrendamiento.buildWithoutSave(tipo:'MENSUAL',producto:prod)
    	when:'salvamos'
    	a.save()
    	then:'El arrendamiento persiste exitosamente'
    	a.errors.errorCount==0
    	a.id
    	Arrendamiento.get(a.id).tipo=='MENSUAL'
    	//println a
    }

    void "Persistencia de rentas a partir del arrendamiento"(){
        setup:
        def prod=Producto.build(clave:'RENTA_MENSUAL')
        def periodo=new Periodo('01/01/2015','31/12/2015')
        def a=Arrendamiento.buildWithoutSave(
            tipo:'MENSUAL',
            producto:prod,
            inicio:periodo.fechaInicial,
            fin:periodo.fechaFinal)
        when:'generamos rentas y salvamos'
        a.generarRentas2()
        a.save()
        then:'El arrendamiento persiste exitosamente'
        Arrendamiento.get(a.id)
        Arrendamiento.get(a.id).rentas.size()==12
        //found.rentas.size()==12

        
    }
}
