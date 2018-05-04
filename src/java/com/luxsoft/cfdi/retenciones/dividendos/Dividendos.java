
package com.luxsoft.cfdi.retenciones.dividendos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 * Complemento para expresar el total de ganancias y utilidades generadas por rendimientos en base a
 * inversiones en instrumentos de inversión
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "divdOUtil",
        "remanente"
})
@XmlRootElement(name = "Dividendos")
public class Dividendos {

    @XmlAttribute(name = "Version", required = true)
    protected String version;

    @XmlElement(name = "DivdOUtil")
    protected Dividendos.DivdOUtil divdOUtil;

    @XmlElement(name = "remanente")
    protected Dividendos.Remanente remanente;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public DivdOUtil getDivdOUtil() {
        return divdOUtil;
    }

    public void setDivdOUtil(DivdOUtil divdOUtil) {
        this.divdOUtil = divdOUtil;
    }
    public Remanente getRemanente() {
        return remanente;
    }

    public void setRemanente(Remanente remanente) {
        this.remanente = remanente;
    }



    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class DivdOUtil {

        /**
         * Atributo requerido para expresar la clave del tipo de dividendo o utilidad distribuida de acuerdo al catálogo
         **/
        @XmlAttribute(name = "CveTipDivOUtil", required = true)
        protected String cveTipDivOUtil;

        /**
         * Atributo requerido para expresar el importe o retención del dividendo o utilidad en territorio nacional
         */
        @XmlAttribute(name = "MontISRAcredRetMexico", required = true)
        protected BigDecimal montISRAcredRetMexico;

        /**
         * Atributo requerido para expresar el importe o retención del dividendo o utilidad en territorio extranjero
         */
        @XmlAttribute(name = "MontISRAcredRetExtranjero", required = true)
        protected BigDecimal montISRAcredRetExtranjero;

        /**
         * Atributo requerido para expresar si el dividendo es distribuido por sociedades nacionales o extranjeras.
         * Valores permitidos:
         *  - Sociedad Nacional
         *  - Sociedad Extranjera
         */
        @XmlAttribute(name = "TipoSocDistrDiv", required = true)
        protected String tipoSocDistrDiv = "Sociedad Nacional";

        /**
         * Atributo opcional para expresar el monto del ISR acreditable nacional
         */
        @XmlAttribute(name = "MontISRAcredNal")
        protected  BigDecimal montISRAcredNal;

        /**
         * Atributo opcional para expresar el monto del dividendo acumulable nacional
         */
        @XmlAttribute(name = "MontDivAcumNal")
        protected  BigDecimal montDivAcumNal;

        /**
         * Atributo opcional para expresar el monto del dividendo acumulable extranjero
         */
        @XmlAttribute(name = "MontDivAcumExt")
        protected  BigDecimal montDivAcumExt;

        public String getCveTipDivOUtil() {
            return cveTipDivOUtil;
        }

        public void setCveTipDivOUtil(String cveTipDivOUtil) {
            this.cveTipDivOUtil = cveTipDivOUtil;
        }
    }

    /**
     * Nodo opcional que expresa el resultado obtenido de la diferencia entre ingresos y egresos de las personas
     * morales que distribuyan anticipos o rendimientos o sociedades de producción, sociedades y asociaciones civiles.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Remanente {

        /**
         * Atributo opcional que expresa el porcentaje de participación de sus integrantes o accionistas
         *
         *  <xs:restriction base="xs:decimal">
         *      <xs:fractionDigits value="6"/>
         *      <xs:maxInclusive value="9999999999"/>
         *  </xs:restriction>
         *
         */
        @XmlAttribute(name = "ProporcionRem")
        protected BigDecimal proporcionRem;

        public BigDecimal getProporcionRem() {
            return proporcionRem;
        }

        public void setProporcionRem(BigDecimal proporcionRem) {
            this.proporcionRem = proporcionRem;
        }
    }
}
