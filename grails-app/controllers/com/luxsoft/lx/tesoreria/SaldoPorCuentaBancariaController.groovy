package com.luxsoft.lx.tesoreria



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.lx.core.PeriodoOperativo
import java.text.NumberFormat
import com.luxsoft.lx.bi.ReportCommand

@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class SaldoPorCuentaBancariaController {

    def saldoPorCuentaBancariaService

    def reportService

    def index() {
        params.sort=params.sort?:'lastUpdated'
        params.order='desc'
        def empresa=session.empresa
        def periodo=session.periodoTesoreria

        def list=SaldoPorCuentaBancaria.findAll(
            "from SaldoPorCuentaBancaria s where s.empresa=? and s.ejercicio=? and s.mes=?",
            [empresa,periodo.ejercicio,periodo.mes],params)
        respond list
    }

    def show(SaldoPorCuentaBancaria saldoPorCuentaBancariaInstance) {

        def ejercicio = saldoPorCuentaBancariaInstance.ejercicio
        def mes = saldoPorCuentaBancariaInstance.mes

        def movimientos = MovimientoDeCuenta.where{
            cuenta == saldoPorCuentaBancariaInstance.cuenta && year(fecha) == ejercicio && month(fecha) == mes
        }.list()

        respond saldoPorCuentaBancariaInstance,model:[movimientos:movimientos]
    }

    @Transactional
    def actualizarSaldos(){
        def periodo=session.periodoTesoreria
        def empresa=session.empresa
        saldoPorCuentaBancariaService.actualizarSaldos(empresa,periodo.ejercicio,periodo.mes)
        flash.message="Actualizacion terminada"
        redirect action:'index'
    }

    def imprimirEstadoDeCuenta(SaldoPorCuentaBancaria saldo){



        
        def cuenta=saldo.cuenta
        
        if(!cuenta)
            throw new RuntimeException("No existe la cuenta: "+id)
        
        
        def periodo=session.periodoTesoreria.toPeriodo().fechaFinal
        
        
        
        def fechaIni=periodo.inicioDeMes()
        def fechaFin=periodo.finDeMes()
        
        def saldoInicial=MovimientoDeCuenta
            .executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(fecha) < ?"
            ,[cuenta,fechaIni])[0]?:0.0
        
        def ingresos=MovimientoDeCuenta.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(x.fecha) between ? and ? and importe>0  "
            ,[cuenta,fechaIni,fechaFin])[0]?:00
        //def egresos=MovimientoDeCuenta.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(x.fecha) between ? and ? and importe<0  "
        def egresos=MovimientoDeCuenta.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(x.fecha) between ? and ? and importe<0  "    
            ,[cuenta,fechaIni,fechaFin])[0]?:00
        def saldoFinal=saldoInicial+(ingresos+egresos)
        
        //def movimientos=MovimientoDeCuenta.findAllByCuentaAndFechaBetween(cuenta,fechaIni,fechaFin,[sort:('fecha')])
        def movimientos=MovimientoDeCuenta.findAll("from MovimientoDeCuenta where date(fecha) between ? and ? and cuenta=? order by fecha,id ",[periodo.inicioDeMes(),periodo.finDeMes(),cuenta])
        def modelData=movimientos.collect { mov ->
            
            def res=[
            //'FOLIO':mov.id
            'FECHA':mov.fecha.format("dd"),
             'CONCEPTO':mov.concepto=='PAGO PROVEEDOR'?mov.aFavor:(mov.concepto=='COBRO'?mov.comentario:mov.concepto)

             ,'TIPO':'ND'
            ,'INGRESO':mov.importe>0?mov.importe.abs():0.0
            ,'EGRESO':mov.importe<0?mov.importe.abs():0.0
             ,'COMENTARIO':mov.comentario
             ,'REFERENCIA':mov.referencia
             ,INI:saldoInicial
             ]
            return res
        }
        NumberFormat nf=NumberFormat.getNumberInstance()
        def repParams=[CUENTA:cuenta.toString()
            ,FECHA_INI:fechaIni.text()
            ,FECHA_FIN:fechaFin.text()
            ,SALDO_INI:nf.format(saldoInicial)
            ,SALDO_FIN:nf.format(saldoFinal)
            ,INGRESOS:nf.format(ingresos.abs())
            ,EGRESOS:nf.format(egresos.abs())
            ,COMPANY:session.empresa.nombre
            ,cuentaId:cuenta.id
            ,periodo:periodo.text()
            ]

        def command=new ReportCommand()
        command.reportName="EstadoDeCuentaBanco"
        command.empresa=session.empresa
        def stream=reportService.build(command,repParams,modelData)
        def file="EstadoDeCuenta_${cuenta.numero}_"+new Date().format('ss')+'.'+command.formato.toLowerCase()
        
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)

        // //repParams<<params
        // log.info 'Ejecutando reporte params:'+repParams+'\n Registros: '+modelData
        // chain(controller:'jasper',action:'index'
        //     ,model:[data:modelData]
        //     ,params:repParams)
        
    }

    def imprimirEstadoDeCuenta2(SaldoPorCuentaBancaria saldo){
        
        def cuenta=saldo.cuenta
        
        if(!cuenta)
            throw new RuntimeException("No existe la cuenta: "+id)
        
        
        def periodo=session.periodoTesoreria.toPeriodo().fechaFinal
        
        def fechaIni=periodo.inicioDeMes()
        def fechaFin=periodo.finDeMes()
        
        def saldoInicial=MovimientoDeCuenta
            .executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(fecha) < ?"
            ,[cuenta,fechaIni])[0]?:0.0
        
        def ingresos=MovimientoDeCuenta
            .executeQuery(
                "select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(x.fecha) between ? and ? and importe>0 and comentario!=?"
            ,[cuenta,fechaIni,fechaFin,'INVERSION'])[0]?:00
        def egresos=MovimientoDeCuenta
            .executeQuery(
                "select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(x.fecha) between ? and ? and importe<0  and comentario!=?"
            ,[cuenta,fechaIni,fechaFin,'INVERSION'])[0]?:00
        
        def saldoFinal=saldoInicial+(ingresos+egresos)
        
        //def movimientos=MovimientoDeCuenta.findAllByCuentaAndFechaBetween(cuenta,fechaIni,fechaFin,[sort:('fecha')])
        def movimientos=MovimientoDeCuenta
            .findAll(
                "from MovimientoDeCuenta where date(fecha) between ? and ? and cuenta=? order by fecha,id ",
                [periodo.inicioDeMes(),periodo.finDeMes(),cuenta])
        def modelData=movimientos.collect { mov ->
            
            def res=[
            //'FOLIO':mov.id
            'FECHA':mov.fecha.format("dd"),
             //'CONCEPTO':mov.concepto=='PAGO_PROVEEDOR'?mov.aFavor:(mov.concepto=='COBRO'?mov.comentario:mov.concepto)
             'CONCEPTO':mov.concepto=='PAGO_PROVEEDOR'?"si es un pago":" No es un pago" //(mov.concepto=='COBRO'?mov.comentario:mov.concepto)
             ,'TIPO':'ND'
            ,'INGRESO':mov.importe>0?mov.importe.abs():0.0
            ,'EGRESO':mov.importe<0?mov.importe.abs():0.0
             ,'COMENTARIO':mov.comentario
             ,'REFERENCIA':mov.referencia
             ,INI:saldoInicial
             ]
            return res
        }
        NumberFormat nf=NumberFormat.getNumberInstance()
        def repParams=[CUENTA:cuenta.toString()
            ,FECHA_INI:fechaIni.text()
            ,FECHA_FIN:fechaFin.text()
            ,SALDO_INI:nf.format(saldoInicial)
            ,SALDO_FIN:nf.format(saldoFinal)
            ,INGRESOS:nf.format(ingresos.abs())
            ,EGRESOS:nf.format(egresos.abs())
            ,COMPANY:session.empresa.nombre
            ,cuentaId:cuenta.id
            ,periodo:periodo.text()
            ]

        def command=new ReportCommand()
        command.reportName="EstadoDeCuentaBanco"
        command.empresa=session.empresa
        def stream=reportService.build(command,repParams,modelData)
        def file="EstadoDeCuenta_${cuenta.numero}_"+new Date().format('ss')+'.'+command.formato.toLowerCase()
        
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)

        // //repParams<<params
        // log.info 'Ejecutando reporte params:'+repParams+'\n Registros: '+modelData
        // chain(controller:'jasper',action:'index'
        //     ,model:[data:modelData]
        //     ,params:repParams)
        
    }
    
}
