databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1500307585536-6") {
		addColumn(tableName: "venta") {
			column(name: "metodo_de_pago", type: "varchar(3)")
		}
		grailsChange {
            change {
                sql.execute("UPDATE venta SET metodo_de_pago = 'PPD'")
            }
            rollback {
            }
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1500307585536-7") {
		addColumn(tableName: "producto") {
			column(name: "impuesto_tasa",  type: "decimal(12,6)")
		}
		grailsChange {
            change {
                sql.execute("UPDATE producto SET impuesto_tasa = 0.16")
            }
            rollback {
            }
		}
	}
}

