package com.luxsoft.lx.cxp



class Gasto extends CuentaPorPagar{
	


    static hasMany = [partidas: GastoDet]

    static mapping = {
		partidas cascade: "all-delete-orphan"
	}

	
}
