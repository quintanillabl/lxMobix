databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438804580652-1") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "comentario", type: "varchar(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438804580652-2") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "cuenta_por_pagar_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438804580652-3") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "fecha", type: "date") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438804580652-4") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438804580652-5") {
		addColumn(tableName: "aplicacion_de_pago") {
			column(name: "pago_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438804580652-8") {
		createIndex(indexName: "FK_cmplskemnbjaola94epnrpskf", tableName: "aplicacion_de_pago") {
			column(name: "cuenta_por_pagar_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438804580652-9") {
		createIndex(indexName: "FK_dln4nkbr14lb3wercwo35nfg9", tableName: "aplicacion_de_pago") {
			column(name: "pago_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1438804580652-6") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_por_pagar_id", baseTableName: "aplicacion_de_pago", constraintName: "FK_cmplskemnbjaola94epnrpskf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_por_pagar", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1438804580652-7") {
		addForeignKeyConstraint(baseColumnNames: "pago_id", baseTableName: "aplicacion_de_pago", constraintName: "FK_dln4nkbr14lb3wercwo35nfg9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "movimiento_de_cuenta", referencesUniqueColumn: "false")
	}
}
