//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.29 at 07:51:27 PM CDT 
//


package com.luxsoft.cfdi.retenciones;

import javax.xml.bind.annotation.XmlRegistry;

import com.luxsoft.cfdi.retenciones.dividendos.Dividendos;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.luxsoft.cfdi.retenciones package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.luxsoft.cfdi.retenciones
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Retenciones }
     * 
     */
    public Retenciones createRetenciones() {
        return new Retenciones();
    }

    /**
     * Create an instance of {@link Retenciones.Totales }
     * 
     */
    public Retenciones.Totales createRetencionesTotales() {
        return new Retenciones.Totales();
    }

    /**
     * Create an instance of {@link Retenciones.Receptor }
     * 
     */
    public Retenciones.Receptor createRetencionesReceptor() {
        return new Retenciones.Receptor();
    }

    /**
     * Create an instance of {@link Retenciones.Emisor }
     * 
     */
    public Retenciones.Emisor createRetencionesEmisor() {
        return new Retenciones.Emisor();
    }

    /**
     * Create an instance of {@link Retenciones.Periodo }
     * 
     */
    public Retenciones.Periodo createRetencionesPeriodo() {
        return new Retenciones.Periodo();
    }

    /**
     * Create an instance of {@link Retenciones.Complemento }
     * 
     */
    public Retenciones.Complemento createRetencionesComplemento() {
        return new Retenciones.Complemento();
    }

    /**
     * Create an instance of {@link Retenciones.Addenda }
     * 
     */
    public Retenciones.Addenda createRetencionesAddenda() {
        return new Retenciones.Addenda();
    }

    /**
     * Create an instance of {@link Retenciones.Totales.ImpRetenidos }
     * 
     */
    public Retenciones.Totales.ImpRetenidos createRetencionesTotalesImpRetenidos() {
        return new Retenciones.Totales.ImpRetenidos();
    }

    /**
     * Create an instance of {@link Retenciones.Receptor.Nacional }
     * 
     */
    public Retenciones.Receptor.Nacional createRetencionesReceptorNacional() {
        return new Retenciones.Receptor.Nacional();
    }

    /**
     * Create an instance of {@link Retenciones.Receptor.Extranjero }
     * 
     */
    public Retenciones.Receptor.Extranjero createRetencionesReceptorExtranjero() {
        return new Retenciones.Receptor.Extranjero();
    }

    public Dividendos createDividendos() {
        return new Dividendos();
    }

    public Dividendos.DivdOUtil createDivdOUtil() {
        return new Dividendos.DivdOUtil();
    }

    public Dividendos.Remanente createRemanente() {
        return new Dividendos.Remanente();
    }

}
