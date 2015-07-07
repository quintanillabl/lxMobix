databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436221740230-1") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "cierre", type: "datetime")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-2") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "cuenta_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-3") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-4") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "egresos", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-5") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "ejercicio", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-6") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-7") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "ingresos", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-8") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-9") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "mes", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-10") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "saldo_final", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-11") {
		addColumn(tableName: "saldo_por_cuenta_bancaria") {
			column(name: "saldo_inicial", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-14") {
		createIndex(indexName: "FK_d9c86iqkf14lw68botjyfhhqt", tableName: "saldo_por_cuenta_bancaria") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-15") {
		createIndex(indexName: "FK_g9ysmmwsdrqk949t7jn81ri9g", tableName: "saldo_por_cuenta_bancaria") {
			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-16") {
		createIndex(indexName: "unique_cuenta_id", tableName: "saldo_por_cuenta_bancaria", unique: "true") {
			column(name: "mes")

			column(name: "ejercicio")

			column(name: "empresa_id")

			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-12") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "saldo_por_cuenta_bancaria", constraintName: "FK_g9ysmmwsdrqk949t7jn81ri9g", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1436221740230-13") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "saldo_por_cuenta_bancaria", constraintName: "FK_d9c86iqkf14lw68botjyfhhqt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}
}
