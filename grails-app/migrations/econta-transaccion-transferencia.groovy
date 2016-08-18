databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1471530011827-1") {
		createTable(tableName: "transaccion_transferencia") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "transaccion_tPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
			column(name: "banco_origen_extranjero", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "banco_origen_nacional_id", type: "bigint") {
				constraints(nullable: "false")
			}
			column(name: "cuenta_origen", type: "varchar(50)")
			

			column(name: "banco_destino_nacional_id", type: "bigint") {
				constraints(nullable: "false")
			}
			column(name: "banco_destino_extranjero", type: "varchar(150)")

			column(name: "cuenta_destino", type: "varchar(50)")

			column(name: "fecha", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "beneficiario", type: "varchar(300)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "varchar(255)") {
				constraints(nullable: "false")
			}
			column(name: "tipo_de_cambio", type: "decimal(19,5)") {
				constraints(nullable: "false")
			}

			column(name: "monto", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "poliza_det_id", type: "bigint") {
				constraints(nullable: "false")
			}
			
			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1471530011827-5") {
		createIndex(indexName: "FK_bmnqxs5vdkyp8xyw7jebhklut", tableName: "transaccion_transferencia") {
			column(name: "banco_origen_nacional_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1471530011827-6") {
		createIndex(indexName: "FK_fhkc9y5hih0w340pbwx0ids1y", tableName: "transaccion_transferencia") {
			column(name: "poliza_det_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1471530011827-7") {
		createIndex(indexName: "FK_qly40db8a0bqbpi7u0lf9t5q2", tableName: "transaccion_transferencia") {
			column(name: "banco_destino_nacional_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1471530011827-8") {
		createIndex(indexName: "poliza_det_id_uniq_1471530011035", tableName: "transaccion_transferencia", unique: "true") {
			column(name: "poliza_det_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1471530011827-2") {
		addForeignKeyConstraint(baseColumnNames: "banco_destino_nacional_id", baseTableName: "transaccion_transferencia", constraintName: "FK_qly40db8a0bqbpi7u0lf9t5q2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "banco_sat", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1471530011827-3") {
		addForeignKeyConstraint(baseColumnNames: "banco_origen_nacional_id", baseTableName: "transaccion_transferencia", constraintName: "FK_bmnqxs5vdkyp8xyw7jebhklut", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "banco_sat", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1471530011827-4") {
		addForeignKeyConstraint(baseColumnNames: "poliza_det_id", baseTableName: "transaccion_transferencia", constraintName: "FK_fhkc9y5hih0w340pbwx0ids1y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "poliza_det", referencesUniqueColumn: "false")
	}
}
