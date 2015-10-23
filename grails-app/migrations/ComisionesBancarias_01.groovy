databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1445620322114-1") {
		createTable(tableName: "comision") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "comisionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(200)")

			column(name: "comision", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto_tasa", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "referencia_bancaria", type: "varchar(100)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445620322114-2") {
		addColumn(tableName: "movimiento_de_cuenta") {
			column(name: "comision_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445620322114-6") {
		createIndex(indexName: "FK_ap9fypdnfs5a167wx9v5m8yjb", tableName: "comision") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445620322114-7") {
		createIndex(indexName: "FK_ple5uo37eiuuu3evcjugrqtd9", tableName: "comision") {
			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445620322114-8") {
		createIndex(indexName: "FK_5cwqojbitrr1qqoqdly2krvt5", tableName: "movimiento_de_cuenta") {
			column(name: "comision_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445620322114-3") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "comision", constraintName: "FK_ple5uo37eiuuu3evcjugrqtd9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445620322114-4") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "comision", constraintName: "FK_ap9fypdnfs5a167wx9v5m8yjb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1445620322114-5") {
		addForeignKeyConstraint(baseColumnNames: "comision_id", baseTableName: "movimiento_de_cuenta", constraintName: "FK_5cwqojbitrr1qqoqdly2krvt5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "comision", referencesUniqueColumn: "false")
	}
}
