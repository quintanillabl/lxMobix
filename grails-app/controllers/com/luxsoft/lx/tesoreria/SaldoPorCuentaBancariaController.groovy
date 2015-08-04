package com.luxsoft.lx.tesoreria



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured


@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class SaldoPorCuentaBancariaController {

    def saldoPorCuentaBancariaService

    def index(Integer max) {
        params.sort=params.sort?:'lastUpdated'
        params.order='desc'
        def empresa=session.empresa
        def periodo=session.periodoContable
        def list=SaldoPorCuentaBancaria.findAll(
            "from SaldoPorCuentaBancaria s where s.empresa=? and s.ejercicio=? and s.mes=?",
            [empresa,periodo.ejercicio,periodo.mes],params)
        respond list
    }

    def show(SaldoPorCuentaBancaria saldoPorCuentaBancariaInstance) {
        respond saldoPorCuentaBancariaInstance
    }

    @Transactional
    def actualizarSaldos(){
        def periodo=session.periodoContable
        def empresa=session.empresa
        saldoPorCuentaBancariaService.actualizarSaldos(empresa,periodo.ejercicio,periodo.mes)
        flash.message="Actualizacion terminada"
        redirect action:'index'
    }
    
}
