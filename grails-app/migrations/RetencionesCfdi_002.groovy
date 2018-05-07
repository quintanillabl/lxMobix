databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1525707859790-1") {
		addColumn(tableName: "cfdi_retenciones") {
			column(name: "cve_tip_divoutil", type: "varchar(255)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1525707859790-2") {
		addColumn(tableName: "cfdi_retenciones") {
			column(name: "monto_div_acum_nal", type: "decimal(19,2)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1525707859790-3") {
		addColumn(tableName: "cfdi_retenciones") {
			column(name: "montoisracred_ret_mexico", type: "decimal(19,2)")
		}
	}
	
}
