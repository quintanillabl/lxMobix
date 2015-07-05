databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1435968030073-1") {
		createTable(tableName: "balanza") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "balanzaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "ejercicio", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "mes", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435968030073-2") {
		createTable(tableName: "balanza_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "balanza_detPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "balanza_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "haber", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "saldo_fin", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "saldo_ini", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435968030073-6") {
		createIndex(indexName: "FK_5gdvx4itskr8hsxvlp2uravti", tableName: "balanza") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435968030073-7") {
		createIndex(indexName: "unique_empresa_id", tableName: "balanza", unique: "true") {
			column(name: "mes")

			column(name: "ejercicio")

			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435968030073-8") {
		createIndex(indexName: "FK_ldyqj0pdpxo6hp47gy4ftawek", tableName: "balanza_det") {
			column(name: "balanza_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435968030073-9") {
		createIndex(indexName: "FK_njx0u65f2vu3ogfigaihyndgv", tableName: "balanza_det") {
			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1435968030073-3") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "balanza", constraintName: "FK_5gdvx4itskr8hsxvlp2uravti", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435968030073-4") {
		addForeignKeyConstraint(baseColumnNames: "balanza_id", baseTableName: "balanza_det", constraintName: "FK_ldyqj0pdpxo6hp47gy4ftawek", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "balanza", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1435968030073-5") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "balanza_det", constraintName: "FK_njx0u65f2vu3ogfigaihyndgv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}
}
