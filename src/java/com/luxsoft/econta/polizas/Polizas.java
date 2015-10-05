
package com.luxsoft.econta.polizas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Poliza" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Transaccion" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CompNal" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="UUID_CFDI" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;whiteSpace value="collapse"/>
 *                                           &lt;length value="36"/>
 *                                           &lt;pattern value="[a-f0-9A-F]{8}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{12}"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="RFC" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="12"/>
 *                                           &lt;maxLength value="13"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                                     &lt;attribute name="TipCamb">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                           &lt;minInclusive value="0"/>
 *                                           &lt;totalDigits value="19"/>
 *                                           &lt;fractionDigits value="5"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CompNalOtr" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="CFD_CBB_Serie">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="1"/>
 *                                           &lt;maxLength value="10"/>
 *                                           &lt;pattern value="[A-Z]+"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="CFD_CBB_NumFol" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                           &lt;minInclusive value="1"/>
 *                                           &lt;totalDigits value="20"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="RFC" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="12"/>
 *                                           &lt;maxLength value="13"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                                     &lt;attribute name="TipCamb">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                           &lt;minInclusive value="0"/>
 *                                           &lt;totalDigits value="19"/>
 *                                           &lt;fractionDigits value="5"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CompExt" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="NumFactExt" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;whiteSpace value="collapse"/>
 *                                           &lt;minLength value="1"/>
 *                                           &lt;maxLength value="36"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="TaxID">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="1"/>
 *                                           &lt;maxLength value="30"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                                     &lt;attribute name="TipCamb">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                           &lt;minInclusive value="0"/>
 *                                           &lt;totalDigits value="19"/>
 *                                           &lt;fractionDigits value="5"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Cheque" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="Num" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;maxLength value="20"/>
 *                                           &lt;minLength value="1"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="BanEmisNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" />
 *                                     &lt;attribute name="BanEmisExt">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;maxLength value="150"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="CtaOri" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;maxLength value="50"/>
 *                                           &lt;minLength value="1"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                                     &lt;attribute name="Benef" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="1"/>
 *                                           &lt;maxLength value="300"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="RFC" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="12"/>
 *                                           &lt;maxLength value="13"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                                     &lt;attribute name="TipCamb">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                           &lt;minInclusive value="0"/>
 *                                           &lt;totalDigits value="19"/>
 *                                           &lt;fractionDigits value="5"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Transferencia" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="CtaOri">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="1"/>
 *                                           &lt;maxLength value="50"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="BancoOriNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" />
 *                                     &lt;attribute name="BancoOriExt">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;maxLength value="150"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="CtaDest" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="1"/>
 *                                           &lt;maxLength value="50"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="BancoDestNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" />
 *                                     &lt;attribute name="BancoDestExt">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;maxLength value="150"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                                     &lt;attribute name="Benef" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="1"/>
 *                                           &lt;maxLength value="300"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="RFC" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="12"/>
 *                                           &lt;maxLength value="13"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                                     &lt;attribute name="TipCamb">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                           &lt;minInclusive value="0"/>
 *                                           &lt;totalDigits value="19"/>
 *                                           &lt;fractionDigits value="5"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="OtrMetodoPago" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="MetPagoPol" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" />
 *                                     &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                                     &lt;attribute name="Benef" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="1"/>
 *                                           &lt;maxLength value="300"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="RFC" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                           &lt;minLength value="12"/>
 *                                           &lt;maxLength value="13"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" />
 *                                     &lt;attribute name="TipCamb">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                           &lt;minInclusive value="0"/>
 *                                           &lt;totalDigits value="19"/>
 *                                           &lt;fractionDigits value="5"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="NumCta" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="100"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="DesCta" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="100"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Concepto" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="200"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Debe" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                           &lt;attribute name="Haber" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="NumUnIdenPol" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;maxLength value="50"/>
 *                       &lt;minLength value="1"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="Concepto" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="300"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.1" />
 *       &lt;attribute name="RFC" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="12"/>
 *             &lt;maxLength value="13"/>
 *             &lt;whiteSpace value="collapse"/>
 *             &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Mes" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="01"/>
 *             &lt;enumeration value="02"/>
 *             &lt;enumeration value="03"/>
 *             &lt;enumeration value="04"/>
 *             &lt;enumeration value="05"/>
 *             &lt;enumeration value="06"/>
 *             &lt;enumeration value="07"/>
 *             &lt;enumeration value="08"/>
 *             &lt;enumeration value="09"/>
 *             &lt;enumeration value="10"/>
 *             &lt;enumeration value="11"/>
 *             &lt;enumeration value="12"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Anio" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="2015"/>
 *             &lt;maxInclusive value="2099"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="TipoSolicitud" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="AF|FC|DE|CO"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="NumOrden">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;length value="13"/>
 *             &lt;pattern value="[A-Z]{3}[0-6][0-9][0-9]{5}(/)[0-9]{2}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="NumTramite">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;length value="10"/>
 *             &lt;pattern value="[0-9]{10}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Sello">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="collapse"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="noCertificado">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;length value="20"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Certificado">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="collapse"/>
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
    "poliza"
})
@XmlRootElement(name = "Polizas")
public class Polizas {

    @XmlElement(name = "Poliza", required = true)
    protected List<Poliza> poliza;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "RFC", required = true)
    protected String rfc;
    @XmlAttribute(name = "Mes", required = true)
    protected String mes;
    @XmlAttribute(name = "Anio", required = true)
    protected int anio;
    @XmlAttribute(name = "TipoSolicitud", required = true)
    protected String tipoSolicitud;
    @XmlAttribute(name = "NumOrden")
    protected String numOrden;
    @XmlAttribute(name = "NumTramite")
    protected String numTramite;
    @XmlAttribute(name = "Sello")
    protected String sello;
    @XmlAttribute(name = "noCertificado")
    protected String noCertificado;
    @XmlAttribute(name = "Certificado")
    protected String certificado;

    /**
     * Gets the value of the poliza property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the poliza property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPoliza().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Poliza }
     * 
     * 
     */
    public List<Poliza> getPoliza() {
        if (poliza == null) {
            poliza = new ArrayList<Poliza>();
        }
        return this.poliza;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "1.1";
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the rfc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRFC() {
        return rfc;
    }

    /**
     * Sets the value of the rfc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRFC(String value) {
        this.rfc = value;
    }

    /**
     * Gets the value of the mes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMes() {
        return mes;
    }

    /**
     * Sets the value of the mes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMes(String value) {
        this.mes = value;
    }

    /**
     * Gets the value of the anio property.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

    /**
     * Gets the value of the tipoSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    /**
     * Sets the value of the tipoSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSolicitud(String value) {
        this.tipoSolicitud = value;
    }

    /**
     * Gets the value of the numOrden property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumOrden() {
        return numOrden;
    }

    /**
     * Sets the value of the numOrden property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumOrden(String value) {
        this.numOrden = value;
    }

    /**
     * Gets the value of the numTramite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumTramite() {
        return numTramite;
    }

    /**
     * Sets the value of the numTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumTramite(String value) {
        this.numTramite = value;
    }

    /**
     * Gets the value of the sello property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSello() {
        return sello;
    }

    /**
     * Sets the value of the sello property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSello(String value) {
        this.sello = value;
    }

    /**
     * Gets the value of the noCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoCertificado() {
        return noCertificado;
    }

    /**
     * Sets the value of the noCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoCertificado(String value) {
        this.noCertificado = value;
    }

    /**
     * Gets the value of the certificado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificado() {
        return certificado;
    }

    /**
     * Sets the value of the certificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificado(String value) {
        this.certificado = value;
    }

}
