databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1445006520496-1") {
		createTable(tableName: "poliza_cheque") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "poliza_chequePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "banco_emisor_extranjero", type: "varchar(150)")

			column(name: "banco_emisor_nacional_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "beneficiario", type: "varchar(300)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_origen", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "date") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "monto", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "numero", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "poliza_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_cambio", type: "decimal(19,5)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445006520496-2") {
		addColumn(tableName: "movimiento_de_cuenta") {
			column(name: "rfc", type: "varchar(13)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445006520496-3") {
		modifyDataType(columnName: "mes", newDataType: "integer", tableName: "poliza_folio")
	}

	changeSet(author: "rcancino (generated)", id: "1445006520496-6") {
		createIndex(indexName: "FK_1oi4oa9p9b1jaipxjdm6dbirm", tableName: "poliza_cheque") {
			column(name: "poliza_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445006520496-7") {
		createIndex(indexName: "FK_9lhkdsk6j7glpwbjt8ippqsjt", tableName: "poliza_cheque") {
			column(name: "banco_emisor_nacional_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445006520496-4") {
		addForeignKeyConstraint(baseColumnNames: "banco_emisor_nacional_id", baseTableName: "poliza_cheque", constraintName: "FK_9lhkdsk6j7glpwbjt8ippqsjt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "banco_sat", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445006520496-5") {
		addForeignKeyConstraint(baseColumnNames: "poliza_id", baseTableName: "poliza_cheque", constraintName: "FK_1oi4oa9p9b1jaipxjdm6dbirm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "poliza", referencesUniqueColumn: "false")
	}
}
