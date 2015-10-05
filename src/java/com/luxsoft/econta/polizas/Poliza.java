
package com.luxsoft.econta.polizas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Transaccion" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CompNal" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="UUID_CFDI" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;length value="36"/>
 *                                 &lt;pattern value="[a-f0-9A-F]{8}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{12}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="RFC" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="12"/>
 *                                 &lt;maxLength value="13"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                           &lt;attribute name="TipCamb">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                 &lt;minInclusive value="0"/>
 *                                 &lt;totalDigits value="19"/>
 *                                 &lt;fractionDigits value="5"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="CompNalOtr" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="CFD_CBB_Serie">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="10"/>
 *                                 &lt;pattern value="[A-Z]+"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="CFD_CBB_NumFol" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                 &lt;minInclusive value="1"/>
 *                                 &lt;totalDigits value="20"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="RFC" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="12"/>
 *                                 &lt;maxLength value="13"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                           &lt;attribute name="TipCamb">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                 &lt;minInclusive value="0"/>
 *                                 &lt;totalDigits value="19"/>
 *                                 &lt;fractionDigits value="5"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="CompExt" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="NumFactExt" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="36"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="TaxID">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="30"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                           &lt;attribute name="TipCamb">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                 &lt;minInclusive value="0"/>
 *                                 &lt;totalDigits value="19"/>
 *                                 &lt;fractionDigits value="5"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Cheque" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="Num" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;maxLength value="20"/>
 *                                 &lt;minLength value="1"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="BanEmisNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" />
 *                           &lt;attribute name="BanEmisExt">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;maxLength value="150"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="CtaOri" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;maxLength value="50"/>
 *                                 &lt;minLength value="1"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                           &lt;attribute name="Benef" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="300"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="RFC" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="12"/>
 *                                 &lt;maxLength value="13"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                           &lt;attribute name="TipCamb">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                 &lt;minInclusive value="0"/>
 *                                 &lt;totalDigits value="19"/>
 *                                 &lt;fractionDigits value="5"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Transferencia" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="CtaOri">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="50"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="BancoOriNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" />
 *                           &lt;attribute name="BancoOriExt">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;maxLength value="150"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="CtaDest" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="50"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="BancoDestNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" />
 *                           &lt;attribute name="BancoDestExt">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;maxLength value="150"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                           &lt;attribute name="Benef" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="300"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="RFC" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="12"/>
 *                                 &lt;maxLength value="13"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                           &lt;attribute name="TipCamb">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                 &lt;minInclusive value="0"/>
 *                                 &lt;totalDigits value="19"/>
 *                                 &lt;fractionDigits value="5"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="OtrMetodoPago" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="MetPagoPol" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" />
 *                           &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                           &lt;attribute name="Benef" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="300"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="RFC" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="12"/>
 *                                 &lt;maxLength value="13"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                           &lt;attribute name="TipCamb">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                 &lt;minInclusive value="0"/>
 *                                 &lt;totalDigits value="19"/>
 *                                 &lt;fractionDigits value="5"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="NumCta" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="100"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="DesCta" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="100"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="Concepto" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="200"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="Debe" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                 &lt;attribute name="Haber" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="NumUnIdenPol" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Concepto" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="300"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "transaccion"
})
public class Poliza {

    @XmlElement(name = "Transaccion", required = true)
    protected List<Transaccion> transaccion;
    @XmlAttribute(name = "NumUnIdenPol", required = true)
    protected String numUnIdenPol;
    @XmlAttribute(name = "Fecha", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fecha;
    @XmlAttribute(name = "Concepto", required = true)
    protected String concepto;

    /**
     * Gets the value of the transaccion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transaccion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransaccion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transaccion }
     * 
     * 
     */
    public List<Transaccion> getTransaccion() {
        if (transaccion == null) {
            transaccion = new ArrayList<Transaccion>();
        }
        return this.transaccion;
    }

    /**
     * Gets the value of the numUnIdenPol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumUnIdenPol() {
        return numUnIdenPol;
    }

    /**
     * Sets the value of the numUnIdenPol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumUnIdenPol(String value) {
        this.numUnIdenPol = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the concepto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Sets the value of the concepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

}
