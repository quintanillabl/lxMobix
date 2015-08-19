databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1439988304543-1") {
		addColumn(tableName: "gasto_det") {
			column(name: "retencion_isr", type: "decimal(19,2)") 
		}
		grailsChange {
            change {
                sql.execute("UPDATE gasto_det SET retencion_isr = 0.0")
            }
            rollback {
            }
		}
		addNotNullConstraint(tableName: "gasto_det", columnDataType:'decimal(19,2)',columnName: "retencion_isr")
	}

	changeSet(author: "rcancino (generated)", id: "1439988304543-2") {
		addColumn(tableName: "gasto_det") {
			column(name: "retencion_isr_tasa", type: "decimal(19,4)") 
		}
		grailsChange {
            change {
                sql.execute("UPDATE gasto_det SET retencion_isr_tasa = 0.0")
            }
            rollback {
            }
		}
		addNotNullConstraint(tableName: "gasto_det", columnDataType:'decimal(19,4)',columnName: "retencion_isr_tasa")
	}

	changeSet(author: "rcancino (generated)", id: "1439988304543-3") {
		addColumn(tableName: "gasto_det") {
			column(name: "retencion_iva", type: "decimal(19,2)") 
		}
		grailsChange {
            change {
                sql.execute("UPDATE gasto_det SET retencion_iva = 0.0")
            }
            rollback {
            }
		}
		addNotNullConstraint(tableName: "gasto_det", columnDataType:'decimal(19,2)',columnName: "retencion_iva")
	}

	changeSet(author: "rcancino (generated)", id: "1439988304543-4") {
		addColumn(tableName: "gasto_det") {
			column(name: "retencion_iva_tasa", type: "decimal(19,4)")
		}
		grailsChange {
            change {
                sql.execute("UPDATE gasto_det SET retencion_iva_tasa = 0.0")
            }
            rollback {
            }
		}
		addNotNullConstraint(tableName: "gasto_det", columnDataType:'decimal(19,4)',columnName: "retencion_iva_tasa")
	}
}
